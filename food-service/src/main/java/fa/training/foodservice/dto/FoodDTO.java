package fa.training.foodservice.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FoodDTO {

    private Integer foodId;
    private String name;
    private Integer price;
    private String ingredient;
    private Integer restaurantId;
    private String status;

}


