package com.example.demo.io.repository;

import com.example.demo.io.entity.FoodEntity;
import com.example.demo.io.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends CrudRepository<FoodEntity, Long> {
    List<FoodEntity> findAllByUserDetails(UserEntity userEntity);
    FoodEntity findByFoodId(String foodId);
}
