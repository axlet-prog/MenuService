package com.example.RestaurantService.service;

import com.example.RestaurantService.controller.dto.MenuItemCreateRequest;
import com.example.RestaurantService.controller.dto.MenuItemPatchRequest;
import com.example.RestaurantService.repository.MenuItem;
import com.example.RestaurantService.repository.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    public final MenuRepository repo;


    public MenuService(MenuRepository repo) {
        this.repo = repo;
    }

    public List<MenuItem> getMenuList() {
        return repo.findAll();
    }

    public MenuItem getMenuItemById(long id) {
        return repo.findById(id).orElseThrow(
                () -> new RuntimeException("Menu not found")
        );
    }

    public MenuItem createMenuItem(MenuItemCreateRequest request) {
        if (repo.findByTitle(request.title).isPresent()) {
            throw new RuntimeException("Menu item with this title already exists already exists");
        }
        try {
            return repo.save(
                    MenuItem.builder()
                            .title(request.title)
                            .description(request.description)
                            .price(Integer.parseInt(request.getPrice()))
                            .build()
            );
        } catch (NumberFormatException e) {
            throw new RuntimeException("Menu item with this title already exists already exists");
        }
    }

    public MenuItem editMenuItem(MenuItemPatchRequest request, long sourceId) {
        MenuItem sourceItem = repo.findById(sourceId).orElseThrow(
                () -> new RuntimeException("Menu item not found")
        );

        if (request.getTitle() != null) sourceItem.setTitle(request.getTitle());
        if (request.getDescription() != null) sourceItem.setDescription(request.getDescription());
        if (request.getPrice() != null) sourceItem.setPrice(Integer.parseInt(request.getPrice()));

        return repo.save(sourceItem);
    }

    public void deleteMenuItem(long id) {
        repo.deleteById(id);
    }


}
