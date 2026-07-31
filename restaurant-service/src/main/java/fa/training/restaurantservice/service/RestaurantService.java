package fa.training.restaurantservice.service;


import fa.training.restaurantservice.dto.ApiResponse;
import fa.training.restaurantservice.dto.RestaurantDTO;

public interface RestaurantService {

    ApiResponse create(RestaurantDTO restaurantDTO);
    ApiResponse update(Integer id, RestaurantDTO restaurant);

    ApiResponse getDetail(Integer id);
    ApiResponse getList(int page, int size, String name, String ownerName);

    ApiResponse deactivate(Integer id);
}
