package com.Internshala.FoodShala.Repository;

import com.Internshala.FoodShala.DAO.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepo extends JpaRepository<Restaurant, Long> {
}
