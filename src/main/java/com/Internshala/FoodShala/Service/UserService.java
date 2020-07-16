package com.Internshala.FoodShala.Service;

import com.Internshala.FoodShala.DAO.User;
import com.Internshala.FoodShala.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User addUser(User user) throws DataIntegrityViolationException {
        User addedUser;
        try {
            addedUser = userRepo.save(user);

        } catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("User exists");
        }
        return addedUser;
    }
}
