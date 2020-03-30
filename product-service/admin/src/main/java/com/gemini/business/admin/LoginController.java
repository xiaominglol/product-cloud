package com.gemini.business.admin;

import com.gemini.boot.framework.web.entity.Message;
import com.gemini.business.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录Controller
 *
 * @author 小明不读书
 * @time 2017-10-27
 */
@RestController
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    UserService userService;

    /**
     * 登陆
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @PostMapping("/login")
    public Message login(
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "remember", required = false) String remember,
            HttpSession session) {
        Map<String, String> map = new HashMap<>();
        map.put("token", "username");
        return Message.success(map);
        //获取当前用户
//        Subject currentUser = SecurityUtils.getSubject();
//        //是否认证通过,即是否已经登录
//        if (!currentUser.isAuthenticated()) {
//            //验证用户名和密码
//            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
//
//                currentUser.login(token);
//                //设置有效时间为15分钟
//                SecurityUtils.getSubject().getSession().setTimeout(3600000);
//                UserInfo user = UserUtils.getCurrentUser();
//                session.setAttribute("user", user);
//                // 成功跳转到主页，防止表单重复提交，应用重定向
//                return "redirect:/index";
//            } catch (AuthenticationException e) {
//                Map<String, Object> map = new HashMap<>();
//                map.put("username", username);
//                //(bug)页面获取不了
//                //model.addAttribute("msg", "登陆失败:" + e.getMessage());
//                //（bug）第一次登陆失败，比如账号不存在，然后登陆成功，然后不注销，再次测试账号不存在，会出现500，session已存在
//                session.setAttribute("msg", "登陆失败:" + e.getMessage());
////                errorLogService.save(ExcpLog.saveExcpLog("/user", map, e.getMessage()));
////                logger.error("登陆失败:" + e.getMessage());
//                // 失败返回登陆页面
//                return "redirect:/";
//            }
//        } else {
//            UserInfo user = UserUtils.getCurrentUser();
//
//            //如果不是同一个用户，则先退出再登陆
//            if (!username.equals(user.getAccount())) {
//                currentUser.logout();
//                this.login(username, password, remember, session);
//            }
//            //如果再次登陆的是同一个用户直接跳转到主页
//            //(bug)我输入任意密码都能登陆
//            return "redirect:/index";
//        }
    }

    @GetMapping("/user/info/detail")
    public Message user(
            @RequestParam(value = "token", required = false) String token) {
        Map<String, String> map = new HashMap<>();
        map.put("name", "token");
        map.put("avatar", "https://xiaominglol.oss-cn-shenzhen.aliyuncs.com/2020/03/29/a04489e5ac5043cf9d5e3cd8c6245cc42e35e02a63f7df98ec963d0ba1663b6.jpg");
        return Message.success(map);
    }
}
