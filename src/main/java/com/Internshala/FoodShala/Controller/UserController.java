package com.Internshala.FoodShala.Controller;

import com.Internshala.FoodShala.DAO.User;
import com.Internshala.FoodShala.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public String addUser(@RequestBody User user) throws DataIntegrityViolationException {
        User addedUser = null;
        try{
            addedUser = userService.addUser(user);
        } catch (DataIntegrityViolationException e){
            System.out.println(e.toString());
        }
           if(addedUser!=null)
                return "\"User Registered\"";
           else
               return "\"User Already Exists\"";

    }

    @GetMapping("/getUser")
    public User getUser(Principal principal) {
        User user;
        try {
            user = userService.getUser(userService.getId(principal));
        } catch(Exception e){
            System.out.println("No User Found");
            return null;
        }
        return user;
    }
    @GetMapping(path = "/validateCustomerLogin", produces = "application/json")
    public String validateCustomerLogin() { return "\"validCustomer\""; }
}
