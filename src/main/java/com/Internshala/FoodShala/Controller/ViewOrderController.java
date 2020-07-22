package com.Internshala.FoodShala.Controller;

import com.Internshala.FoodShala.DAO.User;
import com.Internshala.FoodShala.DAO.ViewOrder;
import com.Internshala.FoodShala.Service.ViewOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.*;

@RestController
@CrossOrigin(origins = "http://foodshalabykrishna.s3-website.us-east-2.amazonaws.com", allowedHeaders = "*")
public class ViewOrderController {

    @Autowired
    private ViewOrderService viewOrderService;


    @GetMapping("/view-orders")
    public ArrayList<ArrayList<ViewOrder>> view(Principal principal) {
       return viewOrderService.getOrdersByRestaurant(principal);

    }


}
