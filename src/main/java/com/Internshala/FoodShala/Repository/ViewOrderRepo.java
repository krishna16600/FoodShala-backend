package com.Internshala.FoodShala.Repository;

import com.Internshala.FoodShala.DAO.ViewOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ViewOrderRepo extends JpaRepository<ViewOrder, Long> {
    List<ViewOrder> findByRestaurantRestaurantId(Long restaurantId);
}
