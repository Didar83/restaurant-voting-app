package com.didarakulov.restaurantvoting.dto;

import com.didarakulov.restaurantvoting.model.LunchMenu;
import com.didarakulov.restaurantvoting.model.Vote;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RestaurantDto {
    private Long id;
    private String name;
    private LunchMenu currentLunchMenu;
    private Double lunchPrice;
    private Integer restaurantVotes;

    public RestaurantDto(String name, LunchMenu currentLunchMenu) {
        this.name = name;
        this.currentLunchMenu = currentLunchMenu;
        lunchPrice = currentLunchMenu.getLunchPrice();
    }
}
