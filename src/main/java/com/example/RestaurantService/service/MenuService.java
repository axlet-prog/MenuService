package com.example.RestaurantService.service;

import com.example.RestaurantService.controller.dto.MenuItemCreateRequest;
import com.example.RestaurantService.controller.dto.MenuItemPatchRequest;
import com.example.RestaurantService.repository.MenuItem;
import com.example.RestaurantService.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Log4j2
@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository repo;

    public List<MenuItem> getMenuList() {
        log.info("Fetching all menu items.");
        List<MenuItem> menuItems = repo.findAll();
        log.info("Found {} menu items.", menuItems.size());
        return menuItems;
    }

    public MenuItem getMenuItemById(long id) {
        log.info("Fetching menu item with ID: {}", id);
        return repo.findById(id).orElseThrow(
            () -> {
                log.warn("Menu item with ID: {} not found.", id);
                return new NoSuchElementException("Menu item not found with id: " + id);
            }
        );
    }

    public MenuItem createMenuItem(MenuItemCreateRequest request) {
        log.info("Attempting to create a new menu item with title: '{}'", request.getTitle());
        if (repo.findByTitle(request.getTitle()).isPresent()) {
            log.warn("Creation failed: Menu item with title '{}' already exists.", request.getTitle());
            throw new IllegalArgumentException("Menu item with this title already exists");
        }
        try {
            MenuItem newItem = MenuItem.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .price(request.getPrice())
                .grams(request.getGrams())
                .build();
            MenuItem savedItem = repo.save(newItem);
            log.info("Successfully created new menu item with ID: {} and title: '{}'", savedItem.getId(), savedItem.getTitle());
            return savedItem;
        } catch (Exception e) {
            log.error("An unexpected error occurred while creating menu item with title '{}': {}", request.getTitle(), e.getMessage());
            throw new RuntimeException("Could not create menu item.", e);
        }
    }

    public MenuItem editMenuItem(MenuItemPatchRequest request, long sourceId) {
        log.info("Attempting to edit menu item with ID: {}", sourceId);
        MenuItem sourceItem = repo.findById(sourceId).orElseThrow(
            () -> {
                log.warn("Edit failed: Menu item with ID: {} not found.", sourceId);
                return new NoSuchElementException("Menu item not found with id: " + sourceId);
            }
        );

        log.debug("Found menu item to edit: {}", sourceItem);

        if (request.getTitle() != null) {
            log.debug("Updating title for item ID {}: from '{}' to '{}'", sourceId, sourceItem.getTitle(), request.getTitle());
            sourceItem.setTitle(request.getTitle());
        }
        if (request.getDescription() != null) {
            log.debug("Updating description for item ID {}", sourceId);
            sourceItem.setDescription(request.getDescription());
        }
        if (request.getPrice() != null) {
            try {
                int newPrice = Integer.parseInt(request.getPrice());
                log.debug("Updating price for item ID {}: from '{}' to '{}'", sourceId, sourceItem.getPrice(), newPrice);
                sourceItem.setPrice(newPrice);
            } catch (NumberFormatException e) {
                log.error("Edit failed: Invalid format for price '{}' for item ID {}.", request.getPrice(), sourceId);
                throw new IllegalArgumentException("Invalid price format provided.");
            }
        }
        if (request.getGrams() != null) {
            log.debug("Updating grams for item ID {}: from '{}' to '{}'", sourceId, sourceItem.getGrams(), request.getGrams());
            sourceItem.setGrams(request.getGrams());
        }

        MenuItem updatedItem = repo.save(sourceItem);
        log.info("Successfully updated menu item with ID: {}", sourceId);
        return updatedItem;
    }

    public void deleteMenuItem(long id) {
        log.info("Attempting to delete menu item with ID: {}", id);
        if (!repo.existsById(id)) {
            log.warn("Delete failed: Menu item with ID: {} not found.", id);
            throw new NoSuchElementException("Menu item not found with id: " + id);
        }
        repo.deleteById(id);
        log.info("Successfully deleted menu item with ID: {}", id);
    }
}