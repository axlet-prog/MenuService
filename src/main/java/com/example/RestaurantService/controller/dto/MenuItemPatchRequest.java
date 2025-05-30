package com.example.RestaurantService.controller.dto;

import lombok.Data;

@Data
public class MenuItemPatchRequest {
    public String title = null;
    public String description = null;
    public String price = null;
}
