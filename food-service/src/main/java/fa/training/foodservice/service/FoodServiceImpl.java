package fa.training.foodservice.service;


import fa.training.foodservice.dto.ApiResponse;
import fa.training.foodservice.dto.FoodDTO;
import fa.training.foodservice.dto.PageDTO;
import fa.training.foodservice.entity.Food;
import fa.training.foodservice.repository.FoodRepository;
import fa.training.foodservice.specification.FoodSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;

    public FoodServiceImpl(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public FoodDTO mapDTO(Food food) {
        FoodDTO foodDTO = FoodDTO.builder()
                .foodId(food.getFoodId())
                .name(food.getName())
                .price(food.getPrice())
                .ingredient(food.getIngredient())
                .restaurantId(food.getRestaurantId())
                .status(food.getStatus())
                .build();

        return foodDTO;
    }

    public Food mapToEntity(FoodDTO foodDTO) {
        Food food = Food.builder()
                .foodId(foodDTO.getFoodId())
                .name(foodDTO.getName())
                .price(foodDTO.getPrice())
                .ingredient(foodDTO.getIngredient())
                .restaurantId(foodDTO.getRestaurantId())
                .status(foodDTO.getStatus())
                .build();

        return food;
    }

    @Override
    public ApiResponse createFood(FoodDTO food) {
        try{
            if(food.getName() == null || food.getPrice() == null || food.getRestaurantId() == null){
                return new ApiResponse(3, "The field of request is null", null);
            }

            Food saved = mapToEntity(food);
            foodRepository.save(saved);
            return new ApiResponse(1, "Food created successfully", saved);
        }
        catch (Exception e){
            return new ApiResponse(2, e.getMessage(), null);
        }
    }

    @Override
    public ApiResponse updateFood(Integer id, FoodDTO food) {
        try{
            Food foodToUpdate = foodRepository.findById(id).orElse(null);
            if(foodToUpdate == null){
                return new ApiResponse(3, "Food not found", null);
            }
            if(food.getName() != null) foodToUpdate.setName(food.getName());
            if(food.getPrice() != null) foodToUpdate.setPrice(food.getPrice());
            if(food.getRestaurantId() != null) foodToUpdate.setRestaurantId(food.getRestaurantId());
            if(food.getStatus() != null) foodToUpdate.setStatus(food.getStatus());

            return new ApiResponse(1, "Food updated successfully", foodToUpdate);
        } catch(Exception e){
            return new ApiResponse(2, e.getMessage(), null);
        }
    }

    @Override
    public ApiResponse getFoodDetail(Integer id) {
        try{
            Food food = foodRepository.findById(id).orElse(null);
            if(food == null) return new ApiResponse(4, "Food not found", null);
            return new ApiResponse(1, "Food found", food);
        }
        catch(Exception e){
            return new ApiResponse(0, e.getMessage(), null);
        }
    }


    @Override
    public ApiResponse getFoodList(int page, int size, String name, String ingredients) {
        try{
            Pageable pageable = PageRequest.of(page, size);
            Specification<Food> spec = FoodSpecification.getFoodSpecification(name, ingredients);
            Page<Food> foodPage = foodRepository.findAll(spec, pageable);

            List<FoodDTO> foodDTOList = foodPage.getContent().stream()
                    .map(this::mapDTO)
                    .collect(Collectors.toList());

            PageDTO pageDTO = PageDTO.builder()
                    .size(foodPage.getSize())
                    .first(foodPage.isFirst())
                    .last(foodPage.isLast())
                    .page(foodPage.getNumber())
                    .totalPages(foodPage.getTotalPages())
                    .totalElements(foodPage.getTotalElements())
                    .content(foodDTOList)
                    .build();

            return new ApiResponse(1, "Success", pageDTO);
        }catch(Exception e){
            return new ApiResponse(0, e.getMessage(), null);
        }
    }

    @Override
    public ApiResponse deactiveFood(Integer id) {
        try{
            Food food = foodRepository.findById(id).orElse(null);
            if(food == null){
                return new ApiResponse(4, "Food is not found", null);
            }
            food.setStatus("INACTIVE");
            foodRepository.save(food);
            return new  ApiResponse(1, "Food is deactivated successfully", food);
        }
        catch(Exception e){
            return new ApiResponse(0, e.getMessage(), null);
        }
    }
}
