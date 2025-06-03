package com.example.RestaurantService.controller.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class MenuItemCreateRequest {
    private String title;
    private String description;
    private int price;
    private int grams;
}
