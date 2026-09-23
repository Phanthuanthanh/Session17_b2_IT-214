package com.example.service;

import com.example.model.MenuItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RestaurantService {

    private static final Logger log = LoggerFactory.getLogger(RestaurantService.class);

    @Cacheable(value = "restaurantMenu", key = "#id")
    public List<MenuItem> getMenuByRestaurantId(Long id) {
        log.info("Querying database for menu of restaurant ID: {}...", id);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        if (Long.valueOf(101).equals(id)) {
            return Arrays.asList(
                    new MenuItem(1L, 101L, "Phở Bò", 55000.0),
                    new MenuItem(2L, 101L, "Bún Chả", 60000.0)
            );
        }

        return Arrays.asList(
                new MenuItem(1L, id, "Món mặc định 1", 30000.0),
                new MenuItem(2L, id, "Món mặc định 2", 45000.0)
        );
    }
}
