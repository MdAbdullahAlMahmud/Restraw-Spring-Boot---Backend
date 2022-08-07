package com.example.demo.io.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "foods")
public class FoodEntity implements Serializable {


    @Id
    @GeneratedValue
    private long id;

    @Column(length = 100,nullable = false)
    private String name;

    @Column(nullable = false)
    private String image;

    @Column(length = 30,nullable = false)
    private String foodId;

    @Column(nullable = false)
    private double price;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private UserEntity userDetails;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public UserEntity getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserEntity userDetails) {
        this.userDetails = userDetails;
    }
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
