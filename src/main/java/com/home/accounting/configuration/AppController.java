package com.home.accounting.configuration;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
/*@RequestMapping("/")*/
public class AppController {

    /*@RequestMapping("/")*/
    public String onHome(Model model) {
        return "home";
    }


    @RequestMapping("/")
    public String authentication(Model model) {
        return "authentication";
    }

    /*registration new user*/
    @RequestMapping("/registration")
    public String registration(Model model) {
        return "registration";
    }  /*authentication_action user*/

    @RequestMapping("/authentication_action")
    public String authenticationAction(Model model) {
        return "account";
    }

    @RequestMapping("/add_user")
    public String addUser(Model model) {
        return "add_user";
    }

    @RequestMapping("/add_category")
    public String addCategory(Model model) {
        return "add_category";
    }

    @RequestMapping("/add_operation")
    public String addOperation(Model model) {
        return "add_operation";
    }

}
