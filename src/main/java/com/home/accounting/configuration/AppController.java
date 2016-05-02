package com.home.accounting.configuration;

import com.home.accounting.entity.Account;
import com.home.accounting.entity.Category;
import com.home.accounting.entity.User;
import com.home.accounting.service.AccountService;
import com.home.accounting.service.CategoryService;
import com.home.accounting.service.OperationService;
import com.home.accounting.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

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
    @Autowired
    MessageSource messageSource;

    /*@RequestMapping("/")*/
    public String onHome(Model model) {
        return "home";
    }


    @RequestMapping(value = {"/", "authentication"})
    public String authentication(Model model) {
        return "authentication";
    }

    /*registration new user*/
  /*  @RequestMapping("/registration")
    public String registration(Model model) {
        return "registration";
    }  *//*authentication_action user*/

    @RequestMapping("/authentication_action")
    public String authenticationAction(Model model, @RequestParam(value = "user_name") String userName, @RequestParam String password) {
        User user = userService.findUserByName(userName);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                model.addAttribute("user", user);
                return "redirect:/operations";
            }
        }
        model.addAttribute("errorMessage", "No such user");
        return "redirect:/authentication";
    }

    @RequestMapping("/add_user")
    public String addUser(Model model, @RequestParam String login,
                          @RequestParam String password, @RequestParam String email,
                          @RequestParam(value = "full_name") String fullName, @RequestParam int age) {
        User userFind = userService.findUserByName(login);
        if (userFind != null) {
            model.addAttribute("errorLogin", "This login using");
            return "redirect:/registration";
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
        return "redirect:/operations";
    }

    //User
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String newUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("edit", false);
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String saveUser(Model model, @Valid User user, BindingResult result) {
        if (result.hasErrors()) return "registration";
        boolean login = userService.isUserUniqueLogin(user);
        boolean email = userService.isUserUniqueEmail(user);

        if (login || email) {
            FieldError ssoError;
            if (login) {
                ssoError = new FieldError("user", "login", "Таке значення існує введіть інше значення." /*messageSource.getMessage("non.unique.ssoId", new String[]{category.getName()}, Locale.getDefault())*/);
                result.addError(ssoError);
            }
            if (email) {
                ssoError = new FieldError("user", "email", "Таке значення існує введіть інше значення." /*messageSource.getMessage("non.unique.ssoId", new String[]{category.getName()}, Locale.getDefault())*/);
                result.addError(ssoError);
            }
            return "registration";
        }
        userService.addUser(user);
        Account account = new Account();
        account.setUser(user);
        user.setAccount(account);
        accountService.addAccount(account);
        userService.addUser(user);
        model.addAttribute("user", user);
        return "redirect:/authentication";
    }

    @RequestMapping(value = {"/edit-user-{id}"}, method = RequestMethod.GET)
    public String editUser(@PathVariable long id, ModelMap model) {
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("edit", true);
        return "redirect:/registration";
    }

    @RequestMapping(value = {"/edit-user-{id}"}, method = RequestMethod.POST)
    public String updateUser(@Valid User user, BindingResult result,
                             ModelMap model, @PathVariable long id) {
        if (result.hasErrors()) return "registration";

        boolean login = userService.isUserUniqueLogin(user.getLogin(), id);
        boolean email = userService.isUserUniqueEmail(user.getEmail(), id);

        if (!login || !email) {
            FieldError ssoError;
            if (login) {
                ssoError = new FieldError("user", "login", "Таке значення існує введіть інше значення." /*messageSource.getMessage("non.unique.ssoId", new String[]{category.getName()}, Locale.getDefault())*/);
                result.addError(ssoError);
            }
            if (email) {
                ssoError = new FieldError("user", "email", "Таке значення існує введіть інше значення." /*messageSource.getMessage("non.unique.ssoId", new String[]{category.getName()}, Locale.getDefault())*/);
                result.addError(ssoError);
            }
            return "redirect:/registration";
        }

        userService.editUser(user);
        model.addAttribute("users", userService.listAllUsers());
        return "redirect:/users";
    }

    @RequestMapping(value = {"/delete-user-{id}"}, method = RequestMethod.GET)
    public String deleteUser(@PathVariable Long id, ModelMap model) {
        userService.deleteUserById(id);
        model.addAttribute("users", userService.listAllUsers());
        return "redirect:/users";
    }

    //show users
    @RequestMapping("/users")
    public String showUsers(Model model) {
        model.addAttribute("users", userService.listAllUsers());
        return "users";
    }


    //Category
    @RequestMapping(value = "/add_category", method = RequestMethod.GET)
    public String newCategory(Model model) {
        Category category = new Category();
        model.addAttribute("category", category);
        model.addAttribute("edit", false);
        return "add_category";
    }

    @RequestMapping(value = {"/add_category"}, method = RequestMethod.POST)
    public String saveCategory(Model model, @Valid Category category, BindingResult result) {
        if (result.hasErrors()) return "add_category";
        if (!categoryService.isCategoryUnique(category)) {
            FieldError ssoError = new FieldError("category", "name", "Таке значення існує введіть інше значення." /*messageSource.getMessage("non.unique.ssoId", new String[]{category.getName()}, Locale.getDefault())*/);
            result.addError(ssoError);
            return "add_category";
        }
        categoryService.addCategory(category);
        model.addAttribute("categories", categoryService.listAllCategories());
        return "redirect:/categories";
    }


    @RequestMapping(value = {"/edit-category-{id}"}, method = RequestMethod.GET)
    public String editCategory(@PathVariable long id, ModelMap model) {
        Category category = categoryService.findCategory(id);
        model.addAttribute("category", category);
        model.addAttribute("edit", true);
        return "redirect:/add_category";
    }

    @RequestMapping(value = {"/edit-category-{id}"}, method = RequestMethod.POST)
    public String updateCategory(@Valid Category category, BindingResult result,
                                 ModelMap model, @PathVariable long id) {
        if (result.hasErrors()) return "add_category";
        if (!categoryService.isCategoryUnique(category)) {
            FieldError ssoError = new FieldError("category", "name", "Таке значення існує введіть інше значення." /*messageSource.getMessage("non.unique.ssoId", new String[]{category.getName()}, Locale.getDefault())*/);
            result.addError(ssoError);
            return "redirect:/add_category";
        }
        categoryService.editCategory(category);
        model.addAttribute("categories", categoryService.listAllCategories());
        return "redirect:/categories";
    }

    @RequestMapping(value = {"/delete-category-{id}"}, method = RequestMethod.GET)
    public String deleteCategory(@PathVariable Long id, ModelMap model) {
        categoryService.deleteCategory(id);
        model.addAttribute("categories", categoryService.listAllCategories());
        return "redirect:/categories";
    }

    //show categories
    @RequestMapping("/categories")
    public String showCategories(Model model) {
        model.addAttribute("categories", categoryService.listAllCategories());
        return "categories";
    }


    @RequestMapping("/add_operation")
    public String addOperation(Model model) {
        model.addAttribute("categories", categoryService.listAllCategories());
        return "add_operation";
    }

    @RequestMapping("/operations")
    public String operations(Model model) {
        return "operations";
    }


}
