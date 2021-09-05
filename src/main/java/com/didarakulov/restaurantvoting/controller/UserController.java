package com.didarakulov.restaurantvoting.controller;

import com.didarakulov.restaurantvoting.dto.PageDto;
import com.didarakulov.restaurantvoting.dto.RestaurantDto;
import com.didarakulov.restaurantvoting.dto.RestaurantPagingDto;
import com.didarakulov.restaurantvoting.model.Vote;
import com.didarakulov.restaurantvoting.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.time.LocalDateTime;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Get all Restaurants")
    @GetMapping("/get-all-restaurants")
    public ResponseEntity<PageDto<RestaurantDto>> getAllRestaurants(@Valid RestaurantPagingDto restaurantPagingDto){
        return ok(userService.getAllRestaurants(restaurantPagingDto));
    }

    @ApiOperation(value = "User votes for the restaurant. According to conditions an user has only one vote during the day")
    @PostMapping("/addVoteToRestauranr/{userId}/{restaurantId}")
    public ResponseEntity<Vote> addVoteToRestaurant(
            @PathVariable("userId") Long userId,
            @PathVariable("restaurantId") Long restaurantId){
        return ok(userService.addVoteToRestaurant(userId, restaurantId, LocalDateTime.now()));
    }
}
