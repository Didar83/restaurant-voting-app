package com.didarakulov.restaurantvoting.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "dish_unit")
@Data
@NoArgsConstructor
public class DishUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dishTitle;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "lunch_menu_id") // new column with this name in the table "dish_unit"
//    @JsonBackReference
    private LunchMenu lunchMenu;
}
