package com.example;

import com.example.model.MenuItem;
import com.example.service.RestaurantService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RestaurantServiceCacheTest {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CacheManager cacheManager;

    @Test
    void testCacheableBehavior() {
        assertNotNull(cacheManager.getCache("restaurantMenu"));

        long startTime1 = System.currentTimeMillis();
        List<MenuItem> menu1 = restaurantService.getMenuByRestaurantId(101L);
        long duration1 = System.currentTimeMillis() - startTime1;

        assertNotNull(menu1);
        assertEquals(2, menu1.size());
        assertTrue(duration1 >= 2900, "First call should simulate DB delay of ~3000ms");

        long startTime2 = System.currentTimeMillis();
        List<MenuItem> menu2 = restaurantService.getMenuByRestaurantId(101L);
        long duration2 = System.currentTimeMillis() - startTime2;

        assertNotNull(menu2);
        assertEquals(2, menu2.size());
        assertTrue(duration2 < 100, "Second call should be served from cache (<100ms)");

        assertEquals(menu1.get(0).getDishName(), menu2.get(0).getDishName());
        assertEquals(menu1.get(1).getDishName(), menu2.get(1).getDishName());
    }
}
