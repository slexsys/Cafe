package org.cafe.manager.web;

import org.cafe.manager.entity.comp.UserType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {
    @GetMapping("/")
    public String home(HttpServletRequest request) throws Exception {
        if (request.isUserInRole(UserType.Manager.name())) {
            return "/manager/menu";
        } else if (request.isUserInRole(UserType.Waiter.name())) {
            return "/waiter/menu";
        }
        throw new Exception("Invalid Role");
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
