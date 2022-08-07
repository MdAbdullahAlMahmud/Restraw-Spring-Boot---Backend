package com.example.demo.service;

import com.example.demo.shared.dto.FoodDto;
import com.example.demo.shared.dto.RestrawFoodDto;
import com.example.demo.shared.dto.UserDto;
import com.example.demo.ui.model.request.FoodRequestModel;

import java.util.List;

public interface FoodService {

    List<FoodDto> getUserFoods(String userId);
    FoodDto getFood(String addressId);

    void insertFood(RestrawFoodDto restrawFoodDto);
    void  updateFood(FoodRequestModel requestModel ,String userId);



}
