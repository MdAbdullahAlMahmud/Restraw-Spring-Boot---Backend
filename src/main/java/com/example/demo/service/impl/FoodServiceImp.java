package com.example.demo.service.impl;

import com.example.demo.io.entity.FoodEntity;
import com.example.demo.io.entity.UserEntity;
import com.example.demo.io.repository.FoodRepository;
import com.example.demo.io.repository.UserRepository;
import com.example.demo.service.FoodService;
import com.example.demo.shared.dto.FoodDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FoodServiceImp implements FoodService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    FoodRepository foodRepository;
    @Override
    public List<FoodDto> getUserFoods(String userId) {
        List<FoodDto> returnValue = new ArrayList<>();


        ModelMapper modelMapper = new ModelMapper();
        UserEntity userEntity = userRepository.findByUserId(userId);
        if (userEntity==null) return  returnValue;

        Iterable<FoodEntity> foods = foodRepository.findAllByUserDetails(userEntity);

        for (FoodEntity entity:foods) {
            returnValue.add(modelMapper.map(entity, FoodDto.class));

        }


        return returnValue;
    }

    @Override
    public FoodDto getFood(String foodId) {

        FoodDto returnvalue = null;
        FoodEntity foodEntity = foodRepository.findByFoodId(foodId);

        if (foodEntity !=null){
            returnvalue = new ModelMapper().map(foodEntity, FoodDto.class);
        }

        return returnvalue;
    }
}
