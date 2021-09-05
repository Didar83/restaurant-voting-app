package com.didarakulov.restaurantvoting.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "restaurant")
@Data
@NoArgsConstructor
public class Restaurant {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "current_lunch_menu_id")
    private LunchMenu currentLunchMenu;

    @OneToMany(
            mappedBy = "restaurantId",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private Set<Vote> restaurantVotes = new HashSet<>();

}
