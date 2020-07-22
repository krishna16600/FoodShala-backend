package com.Internshala.FoodShala.Controller;

import com.Internshala.FoodShala.DAO.Cart;
import com.Internshala.FoodShala.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://foodshalabykrishna.s3-website.us-east-2.amazonaws.com" , allowedHeaders = "*")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/add-item-cart/{foodId}")
    public String addItemToCart(@PathVariable Long foodId, Principal principal){
        cartService.addItemToCart(foodId,principal);
        return "\"Product Added To Cart\"";
    }

    @Transactional
    @GetMapping("/decrease-quantity/{foodId}")
    public String decrementQuantity(@PathVariable Long foodId, Principal principal){
        return cartService.decrementQuantity(foodId,principal);
    }

    @Transactional
    @GetMapping("/remove-item-cart/{foodId}")
    public String removeItem(@PathVariable Long foodId, Principal principal){
        return cartService.removeProductFromCart(foodId,principal);
    }

    @GetMapping("/show-cart")
    public List<Cart> showCart(Principal principal){
        return cartService.showUserCart(principal);
    }

    @GetMapping("/check-out")
    public void checkout(Principal principal){
        cartService.checkOut(principal);
    }
}
