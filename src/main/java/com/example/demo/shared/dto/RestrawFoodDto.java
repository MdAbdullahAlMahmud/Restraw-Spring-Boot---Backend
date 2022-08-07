package com.example.demo.shared.dto;

import com.example.demo.ui.model.request.FoodRequestModel;

import java.util.List;

public class RestrawFoodDto {
    private long id;
    private String userId;
    private List<FoodRequestModel> foods;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<FoodRequestModel> getFoods() {
        return foods;
    }

    public void setFoods(List<FoodRequestModel> foods) {
        this.foods = foods;
    }
}
