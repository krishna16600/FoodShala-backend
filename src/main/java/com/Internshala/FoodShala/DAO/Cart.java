package com.Internshala.FoodShala.DAO;

import javax.persistence.*;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @ManyToOne
    private User user;

    @ManyToOne
    private Menu foodItem;

    private int quantity;

    public Cart(){

    }

    public Cart(User user, Menu foodItem, int quantity) {
        this.user = user;
        this.foodItem = foodItem;
        this.quantity = quantity;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Menu getFoodItem() {
        return foodItem;
    }

    public void setFoodItem(Menu foodItem) {
        this.foodItem = foodItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
