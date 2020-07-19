package com.Internshala.FoodShala.Controller;

import com.Internshala.FoodShala.DAO.User;
import com.Internshala.FoodShala.DAO.ViewOrder;
import com.Internshala.FoodShala.Service.ViewOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class ViewOrderController {

    @Autowired
    private ViewOrderService viewOrderService;


    @GetMapping("/view-orders")
    public HashMap<Long, List<ViewOrder>> view(Principal principal) {
        HashMap<Long, List<ViewOrder>> hm = new HashMap<>();

        List<ViewOrder> viewOrders = viewOrderService.getOrdersByRestaurant(principal);

        for(ViewOrder order: viewOrders){
            if(!hm.containsKey(order.getUser().getUserId())){
                List<ViewOrder> list = new ArrayList<>();
                list.add(order);
                hm.put(order.getUser().getUserId(), list);
            } else{
                List<ViewOrder> alreadyPresent = hm.get(order.getUser().getUserId());
                alreadyPresent.add(order);
                hm.put(order.getUser().getUserId(), alreadyPresent);
            }
        }

        return hm;
    }


}
