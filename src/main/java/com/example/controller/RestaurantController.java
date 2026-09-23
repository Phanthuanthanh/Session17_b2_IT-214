package com.example.controller;

import com.example.model.MenuItem;
import com.example.service.RestaurantService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/restaurants/{restaurantId}/menu")
    public List<MenuItem> getMenu(@PathVariable Long restaurantId) {
        return restaurantService.getMenuByRestaurantId(restaurantId);
    }
}
