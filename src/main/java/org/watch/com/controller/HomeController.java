package org.watch.com.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@RestController
@RequestMapping("/home")
public class HomeController {
    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest httpServletRequest) {
        Cookie[] cookies = httpServletRequest.getCookies();
        String token = "";
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals("token")) {
                token = cookies[i].getValue();
                continue;
            }
        }
        return new ModelAndView("/home/index")
                .addObject("user", token);
    }
}
