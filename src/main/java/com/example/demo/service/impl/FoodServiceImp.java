package com.example.demo.service.impl;

import com.example.demo.exception.FoodServiceException;
import com.example.demo.io.entity.FoodEntity;
import com.example.demo.io.entity.UserEntity;
import com.example.demo.io.repository.FoodRepository;
import com.example.demo.io.repository.UserRepository;
import com.example.demo.service.FoodService;
import com.example.demo.shared.Utils;
import com.example.demo.shared.dto.FoodDto;
import com.example.demo.shared.dto.RestrawFoodDto;
import com.example.demo.shared.dto.UserDto;
import com.example.demo.ui.model.request.FoodRequestModel;
import com.example.demo.ui.model.response.ErrorMessage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FoodServiceImp implements FoodService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    FoodRepository foodRepository;

    @Autowired
    Utils utils;
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

    @Override
    public void insertFood(RestrawFoodDto foodDto) {

        UserEntity userEntity = userRepository.findByUserId(foodDto.getUserId());
        if (foodDto.getFoods().size()<=0){
            throw  new FoodServiceException(ErrorMessage.MISSING_REQUIRED_FIELD.getErrorMessage());
        }
        List<FoodEntity> foodEntityList = new ArrayList<>();
        for (int i = 0; i < foodDto.getFoods().size(); i++) {
          FoodEntity foodEntity = new FoodEntity();
          FoodRequestModel requestModel = foodDto.getFoods().get(i);

          foodEntity.setImage(requestModel.getImage());
          foodEntity.setName(requestModel.getName());
          foodEntity.setPrice(requestModel.getPrice());
          foodEntity.setUserDetails(userEntity);
          foodEntity.setFoodId(utils.generateFoodId(30));
          foodEntityList.add(foodEntity);
        }
        foodRepository.saveAll(foodEntityList);
    }

    @Override
    public void updateFood(FoodRequestModel requestModel,String userId) {

        FoodDto dbFoodEntity = getFood(requestModel.getFoodId());
        UserEntity userEntity = userRepository.findByUserId(userId);

        FoodEntity foodEntity = new FoodEntity();
        foodEntity.setId(dbFoodEntity.getId());

        foodEntity.setUserDetails(userEntity);
        foodEntity.setFoodId(requestModel.getFoodId());
        foodEntity.setPrice(requestModel.getPrice());
        foodEntity.setName(requestModel.getName());
        foodEntity.setImage(requestModel.getImage());


        foodRepository.save(foodEntity);
    }
}
