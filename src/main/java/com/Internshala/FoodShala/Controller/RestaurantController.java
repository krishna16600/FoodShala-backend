package com.Internshala.FoodShala.Controller;

import com.Internshala.FoodShala.DAO.Restaurant;
import com.Internshala.FoodShala.Service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
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
}
