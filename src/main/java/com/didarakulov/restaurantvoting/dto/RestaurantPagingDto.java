package com.didarakulov.restaurantvoting.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;

@Data
@ApiModel(description = "Request model for pagination")
public class RestaurantPagingDto implements Serializable {
    @PositiveOrZero
    @ApiModelProperty(notes = "Start page", required = true)
    private int pageNumber =0;

    @Positive
    @ApiModelProperty(notes = "Entries quantity on the page", required = true, example = "10")
    private int pageSize = 10;

    @ApiModelProperty(notes = "Sorting by users", required = false, example = "restaurantVotes")
    private String sortBy = "restaurantVotes";

    @ApiModelProperty(notes = "Sorting direction", required = false)
    private Sort.Direction direction = Sort.Direction.DESC;

    public RestaurantPagingDto(){}
}
