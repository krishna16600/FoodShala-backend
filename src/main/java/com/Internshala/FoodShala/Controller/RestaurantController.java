package com.Internshala.FoodShala.Controller;

import com.Internshala.FoodShala.DAO.Menu;
import com.Internshala.FoodShala.DAO.Restaurant;
import com.Internshala.FoodShala.Service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://foodshalabykrishna.s3-website.us-east-2.amazonaws.com", allowedHeaders = "*")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/addRestaurant")
    public Restaurant addRestaurant(@RequestBody Restaurant restaurant) throws DataIntegrityViolationException {
        Restaurant addedRestaurant = null;
        try{
            addedRestaurant = restaurantService.addRestaurant(restaurant);
        } catch (DataIntegrityViolationException e){
            System.out.println(e.toString());
        }
        return addedRestaurant;
    }

    @GetMapping(path = "/validateRestaurantLogin", produces = "application/json")
    public String validateRestaurantLogin() {
        return "\"validRestaurant\"";
    }

    @GetMapping("/getAllRestaurants")
    public List<Restaurant> getRestaurants(){
        return restaurantService.getAllRestaurants();
    }

    @GetMapping("/check-role/{email}")
    public String getRole(@PathVariable String email){
        String role = "\"restaurant\"";
        try{
            role = restaurantService.getRole(email);
        }catch(Exception e){
            System.out.println("Error in finding restaurant!");
        }
        return role;
    }

    @GetMapping("/get-restaurant")
    public Restaurant getRestaurant(Principal principal){
        return restaurantService.getRestaurant(principal);
    }

    @GetMapping("/get-menu")
    public List<Menu> getmenu(Principal principal){
        return restaurantService.getMenu(principal);
    }
}
