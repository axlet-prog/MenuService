package com.example.RestaurantService.controller;

import com.example.RestaurantService.controller.dto.MenuItemCreateRequest;
import com.example.RestaurantService.controller.dto.MenuItemResponse;
import com.example.RestaurantService.controller.dto.MenuListResponse;
import com.example.RestaurantService.service.MenuService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/client")
    public String client() {
        return "hello client";
    }

    @GetMapping("/admin")
    public String admin() {
        return "hello admin";
    }

    @GetMapping("/public")
    public String publicMethod() {
        return "hello public";
    }
    //___________________________________________________________

    @GetMapping
    public ResponseEntity<MenuListResponse> menuList() {
        return ResponseEntity.ok(MenuMapper.mapMenuListToResponse(menuService.getMenuList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItemResponse> menuItemById(@PathVariable long id) {
        return ResponseEntity.ok(MenuMapper.mapMenuItemToResponse(menuService.getMenuItemById(id)));
    }

    @PostMapping()
    public ResponseEntity<MenuItemResponse> createMenuItem(@RequestBody MenuItemCreateRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(MenuMapper.mapMenuItemToResponse(menuService.createMenuItem(request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable long id) {
        menuService.deleteMenuItem(id);
        return ResponseEntity.ok().build();
    }
}
