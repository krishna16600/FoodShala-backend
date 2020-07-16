package com.Internshala.FoodShala.Controller;

import com.Internshala.FoodShala.DAO.Menu;
import com.Internshala.FoodShala.Service.MenuService;
import com.Internshala.FoodShala.Service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class MenuController {

    @Autowired
    private MenuService menuService;
    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/addFoodItem")
    public String addFoodItem(@RequestBody Menu foodItem, Principal principal){
        return menuService.addFoodItem(foodItem, restaurantService.getId(principal));
    }
}
