package com.example.RestaurantService.controller.dto;

import lombok.Data;

@Data
public class MenuItemCreateRequest {
    public String title;
    public String description;
    public String price;
}
