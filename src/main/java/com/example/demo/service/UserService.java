package com.example.demo.service;

import com.example.demo.shared.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface UserService extends UserDetailsService {
    UserDto  createUser(UserDto userDto);
    UserDto  getUser(String email);
    List<UserDto>  getUsers(int  page,int limit);
    UserDto  getUserByUserId(String id);
    UserDto  updateUser(String id,UserDto userDto);
    void  deleteUser(String id);

}
