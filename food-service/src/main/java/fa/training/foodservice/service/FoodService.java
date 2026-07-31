package fa.training.foodservice.service;

import fa.training.foodservice.dto.ApiResponse;
import fa.training.foodservice.dto.FoodDTO;
import fa.training.foodservice.entity.Food;
import org.springframework.stereotype.Service;

public interface FoodService {

    ApiResponse createFood(FoodDTO food);
    ApiResponse updateFood(Integer id, FoodDTO food);

    ApiResponse getFoodDetail(Integer id);
    ApiResponse getFoodList(int page, int size, String name, String ingredients);

    ApiResponse deactiveFood(Integer id);
}
