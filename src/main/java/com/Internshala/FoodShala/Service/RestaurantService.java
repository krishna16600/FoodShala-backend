package com.Internshala.FoodShala.Service;

import com.Internshala.FoodShala.DAO.Restaurant;
import com.Internshala.FoodShala.Repository.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepo restaurantRepo;

    public Restaurant addRestaurant(Restaurant restaurant) throws DataIntegrityViolationException {
       Restaurant addedRestaurant;

        try{
            addedRestaurant = restaurantRepo.save(restaurant);
        } catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("Restaurant already registered");
        }
        return addedRestaurant;
    }

    public List<Restaurant> getAllRestaurants(){
        return restaurantRepo.findAll();
    }
}
