package com.Internshala.FoodShala.Repository;

import com.Internshala.FoodShala.DAO.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepo extends JpaRepository<Menu, Long> {
    List<Menu> findMenuByRestaurantRestaurantId(Long restaurantId);
}
