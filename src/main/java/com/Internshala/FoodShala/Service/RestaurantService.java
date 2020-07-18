package com.Internshala.FoodShala.Service;

import com.Internshala.FoodShala.DAO.Menu;
import com.Internshala.FoodShala.DAO.Restaurant;
import com.Internshala.FoodShala.Repository.MenuRepo;
import com.Internshala.FoodShala.Repository.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepo restaurantRepo;

    @Autowired
    private MenuRepo menuRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Restaurant addRestaurant(Restaurant restaurant) throws DataIntegrityViolationException {
       Restaurant addedRestaurant;

        try{
            addedRestaurant = new Restaurant();
            addedRestaurant.setEmail(restaurant.getEmail());
            addedRestaurant.setAddress(restaurant.getAddress());
            addedRestaurant.setMobileNo(restaurant.getMobileNo());
            addedRestaurant.setPassword(passwordEncoder.encode(restaurant.getPassword()));
            addedRestaurant.setRestaurantName(restaurant.getRestaurantName());
            restaurantRepo.save(addedRestaurant);

        } catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("Restaurant already registered");
        }
        return addedRestaurant;
    }

    public List<Restaurant> getAllRestaurants(){
        return restaurantRepo.findAll();
    }

    public Long getId(Principal principal) {
        Restaurant restaurant = restaurantRepo.findByEmail(principal.getName());
        return restaurant.getRestaurantId();
    }

    public String getRole(String email){
        Restaurant rest = restaurantRepo.findByEmail(email);
        if(rest!=null)
            return "\"restaurant\"";
        else
            return "\"customer\"";
    }

    public Restaurant getRestaurant(Principal principal){
        return restaurantRepo.findByEmail(principal.getName());
    }

    public List<Menu> getMenu(Principal principal){
        Long restId = restaurantRepo.findByEmail(principal.getName()).getRestaurantId();
        return menuRepo.findMenuByRestaurantRestaurantId(restId);
    }
}

