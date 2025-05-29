package com.example.RestaurantService.controller.dto;

import lombok.Builder;

@Builder
public class MenuItemResponse {
    public long id;
    public String title;
    public String description;
    public String price;
}
