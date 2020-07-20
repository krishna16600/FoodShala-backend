package com.Internshala.FoodShala.Service;

import com.Internshala.FoodShala.DAO.Restaurant;
import com.Internshala.FoodShala.DAO.ViewOrder;
import com.Internshala.FoodShala.Repository.ViewOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ViewOrderService {

    @Autowired
    private ViewOrderRepo viewOrderRepo;

   @Autowired
   private RestaurantService restaurantService;


    public ArrayList<ArrayList<ViewOrder>> getOrdersByRestaurant(Principal principal){
        Restaurant rest = restaurantService.getRestaurant(principal);
        List<ViewOrder> orderList = viewOrderRepo.findByRestaurantRestaurantId(rest.getRestaurantId());
        ArrayList<ArrayList<ViewOrder>> result = new ArrayList<>();

        for(int i=0;i<orderList.size();i++){
            ArrayList<ViewOrder> tempOrder = new ArrayList<>();
            Date date = orderList.get(i).getDate();

            while(i<orderList.size() && orderList.get(i).getDate().equals(date)){
                tempOrder.add(orderList.get(i));
                i++;
            }
            result.add(tempOrder);
            i--;
        }
        return result;
    }
}
