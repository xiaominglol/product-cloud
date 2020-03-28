package com.gemini.business.admin.common.config;

import com.gemini.boot.framework.shiro.entity.UserInfo;
import com.gemini.boot.framework.shiro.utils.MD5Util;
import com.gemini.boot.framework.shiro.utils.UserUtils;
import com.gemini.business.admin.enums.StateEnum;
import com.gemini.business.admin.po.LoginLogPo;
import com.gemini.business.admin.po.UserPo;
import com.gemini.business.admin.service.LoginLogService;
import com.gemini.business.admin.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.Set;


/**
 * 自定义shiro
 *
 * @author 小明不读书
 * @date 2017-12-11
 */
public class ShiroRealm extends AuthorizingRealm {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 1、
     * 在自定义realm中注入service会导致该service的@cacheable注解无效
     *
     * @Lazy解决同时使用缓存cache和shiro注解无效问题 2、
     * 如果改了User相关路径，则要清除redis缓存，比如原来User路径为 com.gemini.base.User.java
     * 后来改为com.gemini.test.User,但是缓存里面的是core，这个时候就会报错，找不到core，
     * 所以要清除缓存，重新将新路径的test存进去
     */
    @Lazy
    @Autowired
    UserService userService;
    @Autowired
    LoginLogService loginLogService;

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        LoginLogPo loginLog = new LoginLogPo();

        SimpleAuthenticationInfo saInfo = new SimpleAuthenticationInfo();
        try {
            // 1.把AuthenticationToken转换为UsernamePasswordToken
            UsernamePasswordToken upToken = (UsernamePasswordToken) token;
            // 2.从UsernamePasswordToken获取account
            String account = upToken.getUsername();
            // 3.调用数据库方法,从数据库中查询account的用户
            UserPo user = userService.getByAccount(account);
            loginLog.setUserAccount(account);
            if (user != null) {
                loginLog.setUserName(user.getName());
            }
            // 4.若用户不存在,则抛出UnknownAccountException异常
            if (user == null) {
                throw new UnknownAccountException("账号不存在.");
            }
            // 5.根据用户密码,决定是否抛出其他的AuthenticationException异常
            if (!user.getPassword().equals(MD5Util.encryption(String.valueOf(upToken.getPassword()), user.getAccount()))) {
                throw new AuthenticationException("用户账号或者密码错误.");
            }
            if (user.getStateCode().equals(StateEnum.Disable.name())) {
                throw new LockedAccountException("用户已禁用.");
            }
            loginLog.setLoginStateId(123213L);
            loginLog.setLoginStateCode("12");
            loginLog.setLoginStateName("启用");
            loginLog.setMessage("登陆成功");

            // 盐值
            ByteSource salt = ByteSource.Util.bytes(account);

            // 6.根据用户构建SimpleAuthenticationInfo
            UserInfo userInfo = new UserInfo();
            userInfo.setId(user.getId());
            userInfo.setAccount(user.getAccount());
            userInfo.setName(user.getName());
            userInfo.setPicture(user.getPicture());
            // 3. 根据account查询用户角色
            Set<String> roles = userService.getRoleById(user.getId());
            userInfo.setRoles(roles);
            // 4. 根据account查询用户权限
            Set<String> permissions = userService.getPermissionsById(user.getId());
            userInfo.setPermissions(permissions);

            saInfo = new SimpleAuthenticationInfo(userInfo, user.getPassword(), salt, this.getName());

        } catch (Exception e) {
            //java.lang.ClassCastException: com.gemini.core.module.sys.model.User cannot be cast to com.gemini.base.sys.model.User
            //请看上面第2点注释
            loginLog.setLoginStateId(123213L);
            loginLog.setLoginStateCode("123");
            loginLog.setLoginStateName("禁用");
            loginLog.setMessage(e.getMessage());
            logger.error(e.getMessage());
            throw e;
        } finally {
            loginLogService.insertAsync(loginLog, false);
        }
        return saInfo;
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 1. 从 PrincipalCollection 中来获取登录用户的信息
//        Object account = principals.getPrimaryPrincipal();
        UserInfo currentUser = UserUtils.getCurrentUser();

        // 2. 创建 SimpleAuthorizationInfo
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        // 3. 根据account查询用户角色
        info.setRoles(currentUser.getRoles());

        // 4. 根据account查询用户权限
        info.setStringPermissions(currentUser.getPermissions());

        return info;
    }

    public static void main(String[] args) {
        String algorithmName = "MD5";
        Object source = "123456";
        String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();
        // 随机
        System.out.println(salt2);
        Object salt = ByteSource.Util.bytes("admin");
        System.out.println(salt);
        int hashIterations = 1024;
        Object result = new SimpleHash(algorithmName, source, salt, hashIterations);
        // 038bdaf98f2037b31f1e75b5b4c9b26e
        System.out.println(result);
    }

}
