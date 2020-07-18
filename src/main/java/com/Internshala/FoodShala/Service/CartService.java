package com.Internshala.FoodShala.Service;

import com.Internshala.FoodShala.DAO.Cart;
import com.Internshala.FoodShala.DAO.Menu;
import com.Internshala.FoodShala.DAO.User;
import com.Internshala.FoodShala.Repository.CartRepo;
import com.Internshala.FoodShala.Repository.MenuRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private MenuRepo menuRepo;


    public void addItemToCart(Long foodId, Principal principal){
        User user = userService.getUserByEmail(principal);
        Menu menu = menuRepo.findMenuByFoodId(foodId);

        Cart cart = cartRepo.findByUserAndAndFoodItem(user,menu);

        if(cart!=null){
            cart.setQuantity(cart.getQuantity()+1);
            cartRepo.save(cart);
        } else {
            Cart newCart = new Cart(user, menu, 1);
            cartRepo.save(newCart);
        }
    }

    public String decrementQuantity(Long foodId, Principal principal){
        User user = userService.getUserByEmail(principal);
        Menu menu = menuRepo.findMenuByFoodId(foodId);
        Cart cart = cartRepo.findByUserAndAndFoodItem(user,menu);

        if(cart!=null){
            if(cart.getQuantity()==1){
                return removeProductFromCartHelper(menu,user);
            } else
                cart.setQuantity(cart.getQuantity()-1);
        }
        cartRepo.save(cart);
        return "\"Quantity Decremented\"";
    }

    private String removeProductFromCartHelper(Menu foodItem, User user){
        cartRepo.deleteAllByUserAndFoodItem(user,foodItem);
        return "\"Food Item Deleted From Cart\"";
    }

    public String removeProductFromCart(Long foodId, Principal principal){
        User user = userService.getUserByEmail(principal);
        Menu menu = menuRepo.findMenuByFoodId(foodId);

       return removeProductFromCartHelper(menu,user);
    }

    public List<Cart> showUserCart(Principal principal){
        User user = userService.getUserByEmail(principal);
        return cartRepo.findByUser(user);
    }
}
