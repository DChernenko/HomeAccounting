package com.home.accounting.configuration;

import com.home.accounting.entity.Account;
import com.home.accounting.entity.Category;
import com.home.accounting.entity.Operation;
import com.home.accounting.entity.User;
import com.home.accounting.service.AccountService;
import com.home.accounting.service.CategoryService;
import com.home.accounting.service.OperationService;
import com.home.accounting.service.UserService;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@PropertySource(value = {"classpath:messages.properties"})
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
    @Autowired
    private Environment environment;

    /*@RequestMapping("/")*/
    public String onHome(Model model) {
        return "home";
    }


    @RequestMapping(value = {"/", "authentication"})
    public String authentication(Model model) {
        return "authentication";
    }

    @RequestMapping("/authentication_action")
    public String authenticationAction(Model model, @RequestParam(value = "user_name") String userName, @RequestParam String password) {
        User user = userService.findUserByName(userName);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                model.addAttribute("user", user);
                this.user = user;
                return "redirect:/operations";
            }
        }
        model.addAttribute("errorMessage", "No such user");
        return "redirect:/authentication";
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
                ssoError = new FieldError("user", "login", "This value exists, enter another value." /*messageSource.getMessage("non.unique.ssoId", new String[]{category.getName()}, Locale.getDefault())*/);
                result.addError(ssoError);
            }
            if (email) {
                ssoError = new FieldError("user", "email", environment.getRequiredProperty("non.unique.ssoId")/*"This value exists, enter another value." *//*messageSource.getMessage("non.unique.ssoId", new String[]{category.getName()}, Locale.getDefault())*/);
                result.addError(ssoError);
            }
            return "registration";
        }
        Account account = new Account();
        user.setAccount(account);
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
                ssoError = new FieldError("user", "login", "This value exists, enter another value." /*messageSource.getMessage("non.unique.ssoId", new String[]{category.getName()}, Locale.getDefault())*/);
                result.addError(ssoError);
            }
            if (email) {
                ssoError = new FieldError("user", "email", "This value exists, enter another value." /*messageSource.getMessage("non.unique.ssoId", new String[]{category.getName()}, Locale.getDefault())*/);
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
        if (categoryService.isCategoryUnique(category)) {
            FieldError ssoError = new FieldError("category", "name", "This value exists, enter another value." /*messageSource.getMessage("non.unique.ssoId", new String[]{category.getName()}, Locale.getDefault())*/);
            result.addError(ssoError);
            return "add_category";
        }
        category.setUser(user);
        categoryService.addCategory(category);
        model.addAttribute("categories", categoryService.listCategoriesByUser(user));
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
            FieldError ssoError = new FieldError("category", "name", "This value exists, enter another value." /*messageSource.getMessage("non.unique.ssoId", new String[]{category.getName()}, Locale.getDefault())*/);
            result.addError(ssoError);
            return "redirect:/add_category";
        }
        categoryService.editCategory(category);
        model.addAttribute("categories", categoryService.listCategoriesByUser(user));
        return "redirect:/categories";
    }

    @RequestMapping(value = {"/delete-category-{id}"}, method = RequestMethod.GET)
    public String deleteCategory(@PathVariable Long id, ModelMap model) {
        categoryService.deleteCategory(id);
        //model.addAttribute("categories", categoryService.listAllCategories());
        model.addAttribute("categories", categoryService.listCategoriesByUser(user));
        return "redirect:/categories";
    }

    //show categories
    @RequestMapping("/categories")
    public String showCategories(Model model) {
        // model.addAttribute("categories", categoryService.listAllCategories());
        model.addAttribute("categories", categoryService.listCategoriesByUser(user));
        return "categories";
    }

    //Operation
    @RequestMapping(value = "/add_operation", method = RequestMethod.GET)
    public String newOperation(Model model) {
        Operation operation = new Operation();
        model.addAttribute("operation", operation);
        model.addAttribute("categories", categoryService.listCategoriesByUser(user));
        model.addAttribute("edit", false);
        return "add_operation";
    }

    @RequestMapping(value = {"/add_operation"}, method = RequestMethod.POST)
    public String saveOperation(Model model, @RequestParam(value = "categories") long id,
                                @Valid Operation operation, BindingResult result
            , @RequestParam(value = "date") String date) {
        model.addAttribute("categories", categoryService.listCategoriesByUser(user));
        //if (result.hasErrors()) return "add_operation";
        if (date.isEmpty()) date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        operation.setDate(new LocalDate(date));

        Category category = categoryService.findCategory(id);
        List<Category> categories = new ArrayList<>();
        categories.add(category);
        operation.setCategories(categories);
        operation.setAccount(user.getAccount());
        operationService.addOperation(operation);
        useBalance();
        model.addAttribute("balance", accountService.getBalance(user).getBalance());
        return "redirect:/operations";
    }

    @RequestMapping(value = {"/edit-operation-{id}"}, method = RequestMethod.GET)
    public String editOperation(@PathVariable long id, ModelMap model) {
        Operation operation = operationService.findOperation(id);
        model.addAttribute("categories", categoryService.listCategoriesByUser(user));
        model.addAttribute("operation", operation);
        model.addAttribute("date", operation.getDate().toString());
        model.addAttribute("edit", true);
        return "add_operation";
    }

    @RequestMapping(value = {"/edit-operation-{id}"}, method = RequestMethod.POST)
    public String updateOperation(@Valid Operation operation, BindingResult result,
                                  ModelMap model, @PathVariable long id, @RequestParam(value = "categories") long cagegory_id, @RequestParam(value = "date") String date) {
        Category category = categoryService.findCategory(cagegory_id);
        List<Category> categories = new ArrayList<>();
        categories.add(category);
        if (date.isEmpty()) date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        operation.setDate(new LocalDate(date));
        operation.setCategories(categories);
        operation.setAccount(user.getAccount());
        operationService.editOperation(operation);
        useBalance();
        model.addAttribute("balance", accountService.getBalance(user).getBalance());
        return "redirect:/operations";
    }

    @RequestMapping(value = {"/delete-operation-{id}"}, method = RequestMethod.GET)
    public String deleteOperation(@PathVariable long id, ModelMap model) {
        operationService.deleteOperation(id);
        useBalance();
        model.addAttribute("balance", accountService.getBalance(user).getBalance());
        model.addAttribute("operations", operationService.findAccountOperations(user.getAccount()));
        return "redirect:/operations";
    }


    @RequestMapping("/operations")
    public String operations(Model model) {
        useBalance();
        model.addAttribute("balance", accountService.getBalance(user).getBalance());
        model.addAttribute("operations", operationService.findAccountOperations(user.getAccount()));
        return "operations";
    }

    private void useBalance() {
        List<Operation> operations = operationService.findAccountOperations(user.getAccount());
        double sumAdd = 0;
        double sumSub = 0;
        for (Operation operation : operations) {
            if (operation.isFlagProfit()) sumAdd += operation.getSum();
            else sumSub += operation.getSum();
        }
        user.getAccount().setBalance(sumAdd - sumSub);
        accountService.editAccount(user.getAccount());
    }

}
