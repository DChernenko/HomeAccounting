package com.home.accounting.configuration;

import com.home.accounting.entity.Account;
import com.home.accounting.entity.Category;
import com.home.accounting.entity.User;
import com.home.accounting.service.AccountService;
import com.home.accounting.service.CategoryService;
import com.home.accounting.service.OperationService;
import com.home.accounting.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AppController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private UserService userService;
    @Autowired
    private OperationService operationService;
    @Autowired
    private CategoryService categoryService;
    private User user;

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
    public String authenticationAction(Model model, @RequestParam(value = "user_name") String userName, @RequestParam String password) {
        User user = userService.findUserByName(userName);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                model.addAttribute("user", user);
                return "operations";
            }
        }
        model.addAttribute("errorMessage", "No such user");
        return "authentication";
    }

    @RequestMapping("/add_user")
    public String addUser(Model model, @RequestParam String login,
                          @RequestParam String password, @RequestParam String email,
                          @RequestParam(value = "full_name") String fullName, @RequestParam int age) {
        User userFind = userService.findUserByName(login);
        if (userFind != null) {
            model.addAttribute("errorLogin", "This login using");
            return "registration";
        }
        User user = new User();
        user.setAge(age);
        user.setEmail(email);
        user.setFullName(fullName);
        user.setPassword(password);
        user.setLogin(login);
        userService.addUser(user);
        Account account = new Account();
        user.setAccount(account);
        account.setBalance(0);
        account.setUser(user);
        accountService.addAccount(account);
        userService.addUser(user);
        return "/operations";
    }

    //forward to add_category
    @RequestMapping("/add_category")
    public String addCategoryShow(Model model) {
        return "add_category";
    }

    //add new category
    @RequestMapping("/add_categories")
    public String addCategory(Model model, @RequestParam String name) {
        Category category = new Category();
        category.setName(name);
        categoryService.addCategory(category);
        return "add_category";
    }

    //show categories
    @RequestMapping("/categories")
    public String showCategories(Model model) {
        model.addAttribute("categories", categoryService.listCategories());
        return "categories";
    }

    @RequestMapping(value = {"/edit-category-{id}"}, method = RequestMethod.GET)
    public String editCategory(@PathVariable Long id, ModelMap model) {
        Category category = categoryService.findCategory(id);
        model.addAttribute("category", category);
        model.addAttribute("edit", true);
        return "add_category";
    }

    @RequestMapping(value = {"/delete-category-{id}"}, method = RequestMethod.GET)
    public String deleteCategory(@PathVariable Long id, ModelMap model) {
        try {
            categoryService.deleteCategory(id);
        } catch (Exception e) {
        }
        model.addAttribute("categories", categoryService.listCategories());
        return "categories";
    }


    @RequestMapping("/add_operation")
    public String addOperation(Model model) {
        return "add_operation";
    }

    @RequestMapping("/operations")
    public String operations(Model model) {
        return "/operations";
    }

}
