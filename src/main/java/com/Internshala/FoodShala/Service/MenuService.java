package com.Internshala.FoodShala.Service;

import com.Internshala.FoodShala.DAO.Menu;
import com.Internshala.FoodShala.DAO.Restaurant;
import com.Internshala.FoodShala.Repository.MenuRepo;
import com.Internshala.FoodShala.Repository.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MenuRepo menuRepo;

    @Autowired
    private RestaurantRepo restaurantRepo;

    public String addFoodItem(Menu item, Long restaurantId){
        Restaurant restaurant = restaurantRepo.findByRestaurantId(restaurantId);
        item.setRestaurant(restaurant);
        menuRepo.save(item);
        return "\"Food Item Added Successfully\"";

    }

    public List<Menu> getFoodItems(){
        return menuRepo.findAll();
    }

}
