package com.example.demo.service;

import com.example.demo.shared.dto.FoodDto;

import java.util.List;

public interface FoodService {

    List<FoodDto> getUserFoods(String userId);
    FoodDto getFood(String addressId);

}
