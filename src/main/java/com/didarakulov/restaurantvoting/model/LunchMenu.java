package com.didarakulov.restaurantvoting.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "lunch_menu")
@Data
@NoArgsConstructor
public class LunchMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String lunchTitle;

    private Double lunchPrice;

    private LocalDate lunchMenuDate;

    @OneToMany(
            mappedBy = "lunchMenu",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    Set<DishUnit> lunchMenuSet = new HashSet<>();

    @OneToMany(
            mappedBy = "currentLunchMenu",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private Set<Restaurant> restaurantSet = new HashSet<>();
}
