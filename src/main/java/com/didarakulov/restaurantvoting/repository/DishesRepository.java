package com.didarakulov.restaurantvoting.repository;

import com.didarakulov.restaurantvoting.model.DishUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishesRepository extends JpaRepository<DishUnit, Long> {

}
