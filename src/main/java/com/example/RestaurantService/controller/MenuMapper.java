package com.example.RestaurantService.controller;


import com.example.RestaurantService.controller.dto.MenuItemResponse;
import com.example.RestaurantService.controller.dto.MenuListResponse;
import com.example.RestaurantService.repository.MenuItem;

import java.util.List;

public class MenuMapper {
    static public MenuItemResponse mapMenuItemToResponse(MenuItem menuItem) {
        return MenuItemResponse.builder()
                .id(menuItem.getId())
                .title(menuItem.getTitle())
                .description(menuItem.getDescription())
                .price(String.valueOf(menuItem.getPrice()))
                .build();
    }

    static public MenuListResponse mapMenuListToResponse(List<MenuItem> menuItems) {
        return MenuListResponse.builder()
                .menuList(menuItems.stream().map(MenuMapper::mapMenuItemToResponse).toList())
                .build();
    }
}
