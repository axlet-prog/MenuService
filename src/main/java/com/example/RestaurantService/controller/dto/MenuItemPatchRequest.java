package com.example.RestaurantService.controller.dto;

import lombok.Data;

@Data
public class MenuItemPatchRequest {
    private String title = null;
    private String description = null;
    private String price = null;
    private Integer grams = null;
}
