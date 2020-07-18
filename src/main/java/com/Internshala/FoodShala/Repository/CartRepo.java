package com.Internshala.FoodShala.Repository;

import com.Internshala.FoodShala.DAO.Cart;
import com.Internshala.FoodShala.DAO.Menu;
import com.Internshala.FoodShala.DAO.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepo extends JpaRepository<Cart, Long> {
    Cart findByUserAndAndFoodItem(User user, Menu foodItem);
    Integer deleteAllByUserAndFoodItem(User user, Menu foodItem);
    List<Cart> findByUser(User user);
}
