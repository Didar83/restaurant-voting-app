package com.didarakulov.restaurantvoting.repository;

import com.didarakulov.restaurantvoting.model.User;
import com.didarakulov.restaurantvoting.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface VotesRepository extends JpaRepository<Vote, Long> {

//    @Query("select * from Vote v" +
//            "where userId = :user" +
//            "and v.votingDate = :date")
//    public Vote getVoteByUserIdVotingDate(@Param("user") User user, @Param("date") LocalDate date);

}
