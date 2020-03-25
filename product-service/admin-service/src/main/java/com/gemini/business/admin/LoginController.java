package com.gemini.business.admin;

import com.gemini.boot.framework.shiro.entity.UserInfo;
import com.gemini.boot.framework.shiro.utils.UserUtils;
import com.gemini.business.admin.service.ErrorLogService;
import com.gemini.business.admin.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录Controller
 *
 * @author 小明不读书
 * @time 2017-10-27
 */
@Controller
public class LoginController {

    @Autowired
    ErrorLogService errorLogService;
    @Autowired
    UserService userService;

    /**
     * 跳转到登陆页面
     *
     * @return
     */
    @GetMapping("/")
    public String login() {
        return "login_v3";
    }

    /**
     * 跳转到主页
     *
     * @return
     */
    @GetMapping("/index")
    public String index(HttpSession session) {
        //（优化）待解决更好的清除session
        session.removeAttribute("msg");
        return "index";
    }

    /**
     * 跳转主页
     *
     * @return
     */
    @GetMapping("/home")
    public String home() {
        return "home";
    }

    /**
     * 登陆
     *
     * @param account  用户名
     * @param password 密码
     * @return
     */
    @PostMapping("/login")
    public String login(
            @RequestParam("account") String account,
            @RequestParam("password") String password,
            @RequestParam(value = "remember", required = false) String remember, HttpSession session) {
        //获取当前用户
        Subject currentUser = SecurityUtils.getSubject();
        //是否认证通过,即是否已经登录
        if (!currentUser.isAuthenticated()) {
            //验证用户名和密码
            UsernamePasswordToken token = new UsernamePasswordToken(account, password);
            try {
                currentUser.login(token);
                //设置有效时间为15分钟
                SecurityUtils.getSubject().getSession().setTimeout(3600000);
                UserInfo user = UserUtils.getCurrentUser();
                session.setAttribute("user", user);
                // 成功跳转到主页，防止表单重复提交，应用重定向
                return "redirect:/index";
            } catch (AuthenticationException e) {
                Map<String, Object> map = new HashMap<>();
                map.put("account", account);
                //(bug)页面获取不了
                //model.addAttribute("msg", "登陆失败:" + e.getMessage());
                //（bug）第一次登陆失败，比如账号不存在，然后登陆成功，然后不注销，再次测试账号不存在，会出现500，session已存在
                session.setAttribute("msg", "登陆失败:" + e.getMessage());
//                errorLogService.save(ExcpLog.saveExcpLog("/user", map, e.getMessage()));
//                logger.error("登陆失败:" + e.getMessage());
                // 失败返回登陆页面
                return "redirect:/";
            }
        } else {
            UserInfo user = UserUtils.getCurrentUser();

            //如果不是同一个用户，则先退出再登陆
            if (!account.equals(user.getAccount())) {
                currentUser.logout();
                this.login(account, password, remember, session);
            }
            //如果再次登陆的是同一个用户直接跳转到主页
            //(bug)我输入任意密码都能登陆
            return "redirect:/index";
        }
    }

    /**
     * 跳转到未授权页面
     *
     * @return
     */
    @GetMapping("/403")
    public String goto403() {
        return "common/404";
    }
}
