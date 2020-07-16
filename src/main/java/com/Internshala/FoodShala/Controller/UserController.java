package com.Internshala.FoodShala.Controller;

import com.Internshala.FoodShala.DAO.User;
import com.Internshala.FoodShala.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user) throws DataIntegrityViolationException {
        User addedUser = null;
        try{
            addedUser = userService.addUser(user);
        } catch (DataIntegrityViolationException e){
            System.out.println(e.toString());
        }
            return addedUser;

    }

    @GetMapping(path = "/validateCustomerLogin", produces = "application/json")
    public String validateCustomerLogin() { return "\"validCustomer\""; }
}
