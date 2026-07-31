package fa.training.restaurantservice.service;


import fa.training.restaurantservice.dto.ApiResponse;
import fa.training.restaurantservice.dto.PageDTO;
import fa.training.restaurantservice.dto.RestaurantDTO;
import fa.training.restaurantservice.entity.Restaurant;
import fa.training.restaurantservice.repository.RestaurantRepository;
import fa.training.restaurantservice.specification.SearchSpecification;
import fa.training.restaurantservice.util.MyBeanUtils;
import fa.training.restaurantservice.util.PaginationUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public RestaurantDTO mapDTO(Restaurant restaurant) {
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        MyBeanUtils.copyNonNullProperties(restaurant, restaurantDTO);
        return restaurantDTO;
    }

    public Restaurant mapToEntity(RestaurantDTO restaurant) {
        Restaurant restaurantEntity = new Restaurant();
        MyBeanUtils.copyNonNullProperties(restaurant, restaurantEntity);
        return restaurantEntity;
    }

    @Override
    public ApiResponse create(RestaurantDTO restaurant) {
        try{
            if(restaurant.getOwner() == null || restaurant.getOpenDate() == null || restaurant.getAddress() == null){
                return new ApiResponse(3, "The field of request is null", null);
            }

            Restaurant saved = mapToEntity(restaurant);
            restaurantRepository.save(saved);
            return new ApiResponse(1, "Restaurant created successfully", saved);
        }
        catch (Exception e){
            return new ApiResponse(2, e.getMessage(), null);
        }
    }

    @Override
    public ApiResponse update(Integer id, RestaurantDTO restaurant) {
        try{
            Restaurant restaurantToUpdate = restaurantRepository.findById(id).orElse(null);
            if(restaurantToUpdate == null){
                return new ApiResponse(3, "Restaurant not found", null);
            }

            MyBeanUtils.copyNonNullProperties(restaurant, restaurantToUpdate);
            restaurantRepository.save(restaurantToUpdate);
            return new ApiResponse(1, "Restaurant updated successfully", restaurantToUpdate);
        } catch(Exception e){
            return new ApiResponse(2, e.getMessage(), null);
        }
    }

    @Override
    public ApiResponse getDetail(Integer id) {
        try{
            Restaurant restaurant = restaurantRepository.findById(id).orElse(null);
            if(restaurant == null) return new ApiResponse(4, "Restaurant not found", null);
            return new ApiResponse(1, "Restaurant found", restaurant);
        }
        catch(Exception e){
            return new ApiResponse(0, e.getMessage(), null);
        }
    }


    @Override
    public ApiResponse getList(int page, int size, String name, String ownerName) {
        try {
            Pageable pageable = PageRequest.of(page, size);

            // 1. Lắp ráp các điều kiện tìm kiếm động ngay tại đây cực kỳ linh hoạt
            Specification<Restaurant> spec = Specification.where(SearchSpecification.<Restaurant>contains("name", name))
                    .and(SearchSpecification.contains("owner", ownerName));

            // 2. Lấy dữ liệu
            Page<Restaurant> foodPage = restaurantRepository.findAll(spec, pageable);

            // 3. Map sang DTO
            List<RestaurantDTO> foodDTOList = foodPage.getContent().stream()
                    .map(this::mapDTO)
                    .collect(Collectors.toList());

            // 4. Trả kết quả dùng hàm tiện ích
            PageDTO pageDTO = PaginationUtils.buildPageDTO(foodPage, foodDTOList);

            return new ApiResponse(1, "Success", pageDTO);

        } catch (Exception e) {
            return new ApiResponse(0, e.getMessage(), null);
        }
    }

    @Override
    public ApiResponse deactivate(Integer id) {
        try{
            Restaurant restaurant = restaurantRepository.findById(id).orElse(null);
            if(restaurant == null){
                return new ApiResponse(4, "Restaurant is not found", null);
            }
            restaurant.setStatus("INACTIVE");
            restaurantRepository.save(restaurant);
            return new  ApiResponse(1, "Restaurant is deactivated successfully", restaurant);
        }
        catch(Exception e){
            return new ApiResponse(0, e.getMessage(), null);
        }
    }
}
