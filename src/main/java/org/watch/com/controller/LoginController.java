package org.watch.com.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.watch.com.MyUsernamePasswordToken;
import org.watch.com.model.LoginModel;
import org.watch.com.util.redirect.RedirectUtil;
import org.watch.com.util.resultJson.ResponseResult;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * login
 */
@RestController
public class LoginController {
    private final static Logger log = LoggerFactory
            .getLogger(LoginController.class);

    @Autowired
    private ResponseResult<LoginModel> result;

    /**
     * @param model         LoginModel
     * @param bindingResult BindingResult
     * @return ModelAndView
     */
    @PostMapping("/login")
    public ResponseResult<LoginModel> loginIn(
            @Valid @ModelAttribute("form") LoginModel model,
            BindingResult bindingResult,
            HttpServletResponse response) throws Exception {
        //数据验证
        if (bindingResult.hasErrors()) {
            this.result.setSuccess(false);
            this.result.setCode(400);
            this.result.setMessage(bindingResult.getFieldError().getDefaultMessage());
            return this.result;
        }
        //验证用户和令牌的有效性
        MyUsernamePasswordToken token = new MyUsernamePasswordToken(model.getUsername(),
//此处决定此方法只能用于普通用户
                "user",
                model.getPassword());
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            log.info("获取令牌成功");
            Cookie cookie = new Cookie("token", token.getUsername());
            cookie.setPath("/");
            cookie.setMaxAge(60);
            response.addCookie(cookie);
            this.result.setSuccess(true);
            this.result.setCode(200);
            this.result.setMessage(null);
            this.result.setData(null);
            return this.result;
        } catch (UnknownAccountException e) {
            this.result.setSuccess(false);
            this.result.setCode(400);
            this.result.setMessage(e.getMessage());
            return this.result;
        } catch (Exception e) {
            log.info("获取令牌失败");
            log.info(e.getMessage());
            this.result.setSuccess(false);
            this.result.setCode(400);
            this.result.setMessage("账号密码错误!");
            return this.result;
        }
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,
                               HttpServletResponse response) throws Exception {
        RedirectUtil redirectUtil = new RedirectUtil();
        try {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            Cookie[] cookies = httpServletRequest.getCookies();
            String token_str = "";
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("token")) {
                    token_str = cookies[i].getValue();
                    continue;
                }
            }
            if (token_str != null && !token_str.isEmpty()) {
                Cookie cookie = new Cookie("token", null);
                cookie.setPath("/");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
            Subject subject = SecurityUtils.getSubject();
            if (subject.isAuthenticated()) {
                subject.logout();
                response.getWriter().flush();
                if (log.isDebugEnabled()) {
                    log.debug("用户退出登录");
                }
            }
        } catch (SessionException e) {
            e.printStackTrace();
        }
        return new ModelAndView(redirectUtil.getRedirect() + "/index");
    }

}
