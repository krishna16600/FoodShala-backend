package com.Internshala.FoodShala.Service;

import com.Internshala.FoodShala.DAO.User;
import com.Internshala.FoodShala.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User addUser(User user) throws DataIntegrityViolationException {
        User addedUser;
        try {
           addedUser = new User();
           addedUser.setEmail(user.getEmail());
           addedUser.setGender(user.getGender());
           addedUser.setMobileNo(user.getMobileNo());
           addedUser.setName(user.getName());
           addedUser.setPreference(user.getPreference());
           addedUser.setPassword(passwordEncoder.encode(user.getPassword()));
           userRepo.save(addedUser);

        } catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("User exists");
        }
        return addedUser;
    }

    public Long getId(Principal principal){
        User user = userRepo.findByEmail(principal.getName());
        return user.getUserId();
    }
    public User getUser(Long userId){
        return userRepo.findByUserId(userId);
    }

    public User getUserByEmail(Principal principal){
        return userRepo.findByEmail(principal.getName());
    }
}
