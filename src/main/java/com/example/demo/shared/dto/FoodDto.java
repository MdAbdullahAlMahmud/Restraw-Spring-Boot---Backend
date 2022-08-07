package com.example.demo.shared.dto;

public class FoodDto {
    private long id;
    private String name;
    private String image;
    private String foodId;
    private UserDto userDetails;
    private Long price;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public UserDto getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDto userDetails) {
        this.userDetails = userDetails;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "FoodDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", foodId='" + foodId + '\'' +
                ", userDetails=" + userDetails +
                ", price=" + price +
                '}';
    }
}
