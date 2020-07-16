package com.Internshala.FoodShala.Repository;

import com.Internshala.FoodShala.DAO.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepo extends JpaRepository<Menu, Long> {
}
