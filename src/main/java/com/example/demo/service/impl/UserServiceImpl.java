package com.example.demo.service.impl;

import com.example.demo.exception.UserServiceException;
import com.example.demo.io.repository.UserRepository;
import com.example.demo.io.entity.UserEntity;
import com.example.demo.service.UserService;
import com.example.demo.shared.Utils;
import com.example.demo.shared.dto.FoodDto;
import com.example.demo.shared.dto.UserDto;
import com.example.demo.ui.model.response.ErrorMessage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    Utils utils;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto createUser(UserDto userDto) {

        if (userRepository.findByEmail(userDto.getEmail())!=null)  throw  new RuntimeException("Record Already Exists");

        for (int i = 0; i < userDto.getFoods().size(); i++) {
            FoodDto foodDto = userDto.getFoods().get(i);
            foodDto.setUserDetails(userDto);
            foodDto.setFoodId(utils.generateFoodId(30));
            userDto.getFoods().set(i, foodDto);
        }

        ModelMapper mapper = new ModelMapper();
        UserEntity userEntity = mapper.map(userDto,UserEntity.class);

        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));

        userEntity.setUserId(utils.generateUserId(30));

        UserEntity storedUserDetails = userRepository.save(userEntity);

        UserDto returnValue = mapper.map(storedUserDetails,UserDto.class);
       // BeanUtils.copyProperties(storedUserDetails,returnValue);

        return returnValue;
    }


    @Override
    public UserDto getUser(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity==null) throw new UsernameNotFoundException(email);

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userEntity,userDto);
        return  userDto;


    }

    @Override
    public UserDto getUserByUserId(String id) {

        UserDto userDto = new UserDto();
        UserEntity userEntity = userRepository.findByUserId(id);

        if (userEntity==null) throw new UserServiceException(ErrorMessage.NO_RECORD_FOUND.getErrorMessage());

        BeanUtils.copyProperties(userEntity,userDto);

        return userDto;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity entity =  userRepository.findByEmail(email);

        if (entity==null) throw new UsernameNotFoundException(email);


        return new User(entity.getEmail(),entity.getEncryptedPassword(),new ArrayList<>());
    }

    @Override
    public UserDto updateUser(String id, UserDto userDto)  {
        UserDto returnDto = new UserDto();
        UserEntity userEntity = userRepository.findByUserId(id);

        if (userEntity ==null) throw new UserServiceException(ErrorMessage.NO_RECORD_FOUND.getErrorMessage());

        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        
        UserEntity updatedUser = userRepository.save(userEntity);
        BeanUtils.copyProperties(updatedUser,returnDto);

        return returnDto;
    }

    @Override
    public void deleteUser(String id) {

        UserEntity userEntity = userRepository.findByUserId(id);
        if (userEntity==null) throw new UserServiceException(ErrorMessage.NO_RECORD_FOUND.getErrorMessage());
        userRepository.delete(userEntity);
    }

    @Override
    public List<UserDto> getUsers(int page, int limit) {
        List<UserDto> returnValue = new ArrayList<>();

        if (page>0) page-=1;
        Pageable pageable = PageRequest.of(page,limit);
        Page<UserEntity> userPages = userRepository.findAll(pageable);
        List<UserEntity> userEntities = userPages.getContent();

        for (UserEntity entity : userEntities) {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(entity,userDto);
            returnValue.add(userDto);
        }

        return returnValue;
    }
}
