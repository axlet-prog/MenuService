package com.example.RestaurantService.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MenuItemPatchRequest {
    @JsonProperty("title")
    private String title = null;
    @JsonProperty("description")
    private String description = null;
    @JsonProperty("price")
    private String price = null;
    @JsonProperty("grams")
    private Integer grams = null;
}
