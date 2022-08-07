package com.example.demo.controller;

import com.example.demo.io.entity.UserEntity;
import com.example.demo.service.FoodService;
import com.example.demo.service.UserService;
import com.example.demo.shared.dto.FoodDto;
import com.example.demo.shared.dto.RestrawFoodDto;
import com.example.demo.shared.dto.UserDto;
import com.example.demo.ui.model.request.FoodRequestModel;
import com.example.demo.ui.model.response.FoodRes;
import com.example.demo.ui.model.response.OperationStatusModel;
import com.example.demo.ui.model.response.RequestOperationName;
import com.example.demo.ui.model.response.RequestOperationStatus;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/foods")
public class FoodController {

    @Autowired
    UserService userService;


    @Autowired
    FoodService foodService;


    @GetMapping(path = "/{userId}")
    public List<FoodRes> allFoods(@PathVariable String userId){
        System.out.println("All Food");

        List<FoodRes> foodRes = new ArrayList<>();

        List<FoodDto> foodDtos = foodService.getUserFoods(userId);

        ModelMapper mapper = new ModelMapper();
        Type listType = new TypeToken<List<FoodRes>>(){}.getType();
        foodRes = mapper.map(foodDtos,listType);
        return foodRes;
    }

    @PostMapping()
    public OperationStatusModel insertFoods( @RequestBody RestrawFoodDto restrawFoodDto){
        foodService.insertFood(restrawFoodDto);
        OperationStatusModel operationStatusModel = new OperationStatusModel();
        operationStatusModel.setOperationName(RequestOperationName.INSERT.name());
        operationStatusModel.setOperationResult(RequestOperationStatus.SUCCESS.name());
        return operationStatusModel;
    }


    @GetMapping( "/{userId}/id/{foodId}")
    public FoodRes getSingleFood(@PathVariable("userId")String userId , @PathVariable("foodId") String foodId){
        System.out.println("Single Food");
        FoodRes foodRes = new FoodRes();
        FoodDto foodDtos = foodService.getFood(foodId);
        ModelMapper mapper = new ModelMapper();
        foodRes = mapper.map(foodDtos, FoodRes.class);
        return foodRes;

    }
    @PutMapping( "/{userId}")
    public OperationStatusModel updateSingleFood(@RequestBody FoodRequestModel foodRequestModel , @PathVariable("userId") String userId){

        System.out.println("User ID " + userId);



        foodService.updateFood(foodRequestModel,userId);

        OperationStatusModel operationStatusModel = new OperationStatusModel();
        operationStatusModel.setOperationName(RequestOperationName.UPDATE.name());
        operationStatusModel.setOperationResult(RequestOperationStatus.SUCCESS.name());

        return  operationStatusModel;


    }




}
