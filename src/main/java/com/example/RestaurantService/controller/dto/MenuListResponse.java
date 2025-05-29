package com.example.RestaurantService.controller.dto;

import lombok.Builder;

import java.util.List;

@Builder
public class MenuListResponse {
    public List<MenuItemResponse> menuList;
}
