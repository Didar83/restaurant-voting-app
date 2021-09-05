package com.didarakulov.restaurantvoting.controller;

import com.didarakulov.restaurantvoting.dto.PageDto;
import com.didarakulov.restaurantvoting.dto.RestaurantDto;
import com.didarakulov.restaurantvoting.dto.RestaurantPagingDto;
import com.didarakulov.restaurantvoting.model.Restaurant;
import com.didarakulov.restaurantvoting.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;

    @Autowired
    public AdminController(UserService userService){
        this.userService = userService;
    }

    @ApiOperation(value = "Get all Restaurants")
    @GetMapping("/get-all-restaurants")
    public ResponseEntity<PageDto<RestaurantDto>> getAllRestaurants(@Valid RestaurantPagingDto restaurantPagingDto){
        return ok(userService.getAllRestaurants(restaurantPagingDto));
    }

    @ApiOperation(value = "Add a new restaurant")
    @PostMapping("/addNewRestaurant")
    public ResponseEntity<Restaurant> addNewRestaurant(@RequestBody RestaurantDto restaurantDto){
        return ok(userService.save(restaurantDto));
    }
}
