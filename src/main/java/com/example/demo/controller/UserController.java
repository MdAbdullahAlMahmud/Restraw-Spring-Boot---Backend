package com.example.demo.controller;

import com.example.demo.exception.UserServiceException;
import com.example.demo.service.FoodService;
import com.example.demo.service.UserService;
import com.example.demo.shared.dto.FoodDto;
import com.example.demo.shared.dto.UserDto;
import com.example.demo.ui.model.request.UserDetailsRequestModel;
import com.example.demo.ui.model.response.*;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;


    @Autowired
    FoodService foodService;

    @ApiOperation(value = "Find User By Id",notes = "This end p")
    @GetMapping(path = "/{id}")
    public UserRes getUser(@PathVariable String id){
        UserRes returnValue = new UserRes();
        UserDto userDto = userService.getUserByUserId(id);

        BeanUtils.copyProperties(userDto,returnValue);
        return  returnValue;
    }

    @PostMapping
    public UserRes createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception{



        UserRes returnValue = new UserRes();
        if (userDetails.getEmail().isEmpty()) throw new UserServiceException(ErrorMessage.MISSING_REQUIRED_FIELD.getErrorMessage());

        /*UserDto userDto = new UserDto();

        BeanUtils.copyProperties(userDetails,userDto);*/
        ModelMapper modelMapper = new ModelMapper();
        UserDto userDto = modelMapper.map(userDetails,UserDto.class);
        UserDto createUser = userService.createUser(userDto);

        returnValue = modelMapper.map(createUser,UserRes.class);

        return returnValue;
    }

    @PutMapping(path = {"/{id}"})
    public UserRes  updateUser(@PathVariable String id, @RequestBody UserDetailsRequestModel userDetails) throws  Exception{
        UserRes returnValue = new UserRes();
        UserDto userDto = new UserDto();

        BeanUtils.copyProperties(userDetails,userDto);
        UserDto updatedUser = userService.updateUser(id,userDto);

        BeanUtils.copyProperties(updatedUser,returnValue);

        return returnValue;

    }


    @DeleteMapping(path = "/{id}" )
    public OperationStatusModel deleteUser(@PathVariable String id){


        OperationStatusModel operationStatusModel = new OperationStatusModel();
        operationStatusModel.setOperationName(RequestOperationName.DELETE.name());

        userService.deleteUser(id);

        operationStatusModel.setOperationResult(RequestOperationStatus.SUCCESS.name());

        return  operationStatusModel;
    }

    @GetMapping()
    public List<UserRes> getAllUser(@RequestParam(value = "page",defaultValue = "0") int page,@RequestParam(value = "limit",defaultValue = "2") int limit){
        List<UserRes> allUser = new ArrayList<>();


        List<UserDto> userDtoList = userService.getUsers(page,limit);

        for (UserDto userDto:userDtoList) {
            UserRes userRes = new UserRes();
            BeanUtils.copyProperties(userDto,userRes);
            allUser.add(userRes);
        }

        return  allUser;
    }

    //127.0.0.1/users/{id}/address
    @GetMapping(path = "/{id}/foods")
    public List<FoodRes> getUserAddress(@PathVariable String id){
        List<FoodRes> foodRes = new ArrayList<>();

        List<FoodDto> foodDtos = foodService.getUserFoods(id);

        ModelMapper mapper = new ModelMapper();
        Type listType = new TypeToken<List<FoodRes>>(){}.getType();
        foodRes = mapper.map(foodDtos,listType);

        return foodRes;
    }
    @GetMapping(path = "/{id}/foods/{foodId}")
    public FoodRes getUserAddressById(@PathVariable String foodId){

        FoodRes foodRes = new FoodRes();

        FoodDto foodDtos = foodService.getFood(foodId);

        ModelMapper mapper = new ModelMapper();
        foodRes = mapper.map(foodDtos, FoodRes.class);

        return foodRes;
    }
}
