package com.Internshala.FoodShala.Controller;

import com.Internshala.FoodShala.DAO.Menu;
import com.Internshala.FoodShala.Service.MenuService;
import com.Internshala.FoodShala.Service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://foodshalabykrishna.s3-website.us-east-2.amazonaws.com", allowedHeaders = "*")
public class MenuController {

    @Autowired
    private MenuService menuService;
    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/addFoodItem")
    public String addFoodItem(@RequestBody Menu foodItem, Principal principal){
        return menuService.addFoodItem(foodItem, restaurantService.getId(principal));
    }

    @GetMapping("/getAllFoodItems")
    public List<Menu> getFoodItems(){
        return menuService.getFoodItems();
    }

    @GetMapping("/get-food-item/{foodId}")
    public Menu getItem(@PathVariable Long foodId, Principal principal){
        return menuService.getItem(foodId);
    }

    @PostMapping("/edit-food-item")
    public Menu editItem(@RequestBody Menu item, Principal principal){
        return menuService.editItem(item);
    }

    @Transactional
    @DeleteMapping("/delete-food-item/{id}")
    public String deleteItem(@PathVariable Long id , Principal principal){
        if(menuService.deleteItem(id).equals("1"))
            return "\"Deleted\"";
        return "\"Could not delete\"";
    }
}
