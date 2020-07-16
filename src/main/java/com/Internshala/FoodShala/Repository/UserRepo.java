package com.Internshala.FoodShala.Repository;

import com.Internshala.FoodShala.DAO.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

}
