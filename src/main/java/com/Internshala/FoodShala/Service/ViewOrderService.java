package com.Internshala.FoodShala.Service;

import com.Internshala.FoodShala.DAO.Restaurant;
import com.Internshala.FoodShala.DAO.ViewOrder;
import com.Internshala.FoodShala.Repository.ViewOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class ViewOrderService {

    @Autowired
    private ViewOrderRepo viewOrderRepo;

   @Autowired
   private RestaurantService restaurantService;


    public List<ViewOrder> getOrdersByRestaurant(Principal principal){
        Restaurant rest = restaurantService.getRestaurant(principal);
        return viewOrderRepo.findByRestaurantRestaurantId(rest.getRestaurantId());
    }
}
