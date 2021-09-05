package com.didarakulov.restaurantvoting.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "votes")
@Data
@NoArgsConstructor
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "iser_id")
    private User userId;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurantId;

    private LocalDate votingDate;

    public Vote(User userId, Restaurant restaurantId, LocalDate votingDate) {
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.votingDate = votingDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vote vote = (Vote) o;
        return userId.equals(vote.userId) && votingDate.equals(vote.votingDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, votingDate);
    }
}
