package com.didarakulov.restaurantvoting.service;

import com.didarakulov.restaurantvoting.dto.PageDto;
import com.didarakulov.restaurantvoting.dto.RestaurantDto;
import com.didarakulov.restaurantvoting.dto.RestaurantPagingDto;
import com.didarakulov.restaurantvoting.model.Restaurant;
import com.didarakulov.restaurantvoting.model.Vote;
import com.didarakulov.restaurantvoting.repository.RestaurantsRepository;
import com.didarakulov.restaurantvoting.repository.UsersRepository;
import com.didarakulov.restaurantvoting.repository.VotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    protected RestaurantsRepository restaurantsRepository;
    protected VotesRepository votesRepository;
    protected UsersRepository usersRepository;

    @Autowired
    public UserService(RestaurantsRepository restaurantsRepository, VotesRepository votesRepository, UsersRepository usersRepository){
        this.restaurantsRepository = restaurantsRepository;
        this.votesRepository = votesRepository;
        this.usersRepository = usersRepository;
    }

    public RestaurantDto entityToDto(Restaurant restaurant){
        RestaurantDto restaurantDto = new RestaurantDto();
        if(restaurant.getId() != null) restaurantDto.setId(restaurant.getId());
        restaurantDto.setName(restaurantDto.getName());
        restaurantDto.setCurrentLunchMenu(restaurantDto.getCurrentLunchMenu());
        restaurantDto.setLunchPrice(restaurant.getCurrentLunchMenu().getLunchPrice());
        restaurantDto.setRestaurantVotes(restaurant.getRestaurantVotes().size());
        return restaurantDto;
    }

    public Restaurant DtoToEntity(RestaurantDto restaurantDto){
        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantDto.getName());
        restaurant.setCurrentLunchMenu(restaurantDto.getCurrentLunchMenu());
        return restaurant;
    }

    public PageDto<RestaurantDto> getAllRestaurants(RestaurantPagingDto restaurantPagingDto){
        Pageable pageable = PageRequest.of(restaurantPagingDto.getPageNumber(), restaurantPagingDto.getPageSize(),
                Sort.by(restaurantPagingDto.getDirection(), restaurantPagingDto.getSortBy()));
        Page<Restaurant> restaurants = restaurantsRepository.findAll(pageable);
        List<RestaurantDto> restaurantDtos = new LinkedList<>();
        for(Restaurant restaurant : restaurants){
            restaurantDtos.add(entityToDto(restaurant));
        }
        PageDto<RestaurantDto> pageDto = new PageDto<>(restaurants, restaurantDtos);
        return pageDto;
    }

    public Restaurant save(RestaurantDto restaurantDto) {
        Assert.isTrue(restaurantDto.getCurrentLunchMenu().getLunchMenuSet().size() >=2, "Lunch menu should include 2 or more dishes");
        Assert.isTrue(restaurantDto.getCurrentLunchMenu().getLunchMenuSet().size() <=5, "Lunch menu should include 5 or less dishes");
        return restaurantsRepository.save(DtoToEntity(restaurantDto));
    }

    //TODO Надо придумать как убрать этот костыль
    public Optional <Vote> findVote(Long userId, Long restaurantId){
        List<Vote> votesList = votesRepository.findAll();
        Vote vote = null;
        for(Vote v : votesList){
            if (v.getUserId().getId() == userId && v.getRestaurantId().getId() == restaurantId){
                vote = v;
            }
        }
        return Optional.of(vote);
    }

    public Vote addVoteToRestaurant(Long userId, Long restaurantId, LocalDateTime now) {
        Vote vote;
        LocalDate ld = LocalDate.of(now.getYear(), now.getMonth(), now.getDayOfMonth());
        LocalTime lt = LocalTime.of(11, 00);
        Optional<Vote> voteOpt = findVote(userId, restaurantId);
        if(voteOpt.isPresent()){
            vote = voteOpt.get();
            if(vote.getVotingDate().isEqual(ld)){
                if(now.isBefore(LocalDateTime.of(ld, lt))){
                    vote.setRestaurantId(restaurantsRepository.getById(restaurantId));
                    return votesRepository.save(vote);
                } else {
                    return vote;
                }
            }
        } else {
            vote = new Vote(usersRepository.getById(userId), restaurantsRepository.getById(restaurantId), ld);
        }
        return vote;
    }
}
