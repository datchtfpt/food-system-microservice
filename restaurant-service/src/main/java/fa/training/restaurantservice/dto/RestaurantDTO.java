package fa.training.restaurantservice.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
@Data
public class RestaurantDTO {
    private Integer restaurantId;
    private String name;
    private String owner;
    private Integer priceFrom;
    private Integer priceTo;
    private String phone;
    private String address;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate openDate;
    
    private String status;
    private Integer categoryId;

}


