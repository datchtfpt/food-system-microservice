package fa.training.foodservice.controller;

import fa.training.foodservice.dto.ApiResponse;
import fa.training.foodservice.dto.FoodDTO;
import fa.training.foodservice.entity.Food;
import fa.training.foodservice.service.FoodService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class FoodController {

    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @PostMapping("/foods")
    public ResponseEntity<ApiResponse> createFood(@RequestBody FoodDTO food) {

        ApiResponse apiResponse = foodService.createFood(food);
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/foods/{id}")
    public ResponseEntity<ApiResponse> updateFood(@PathVariable Integer id, @RequestBody FoodDTO food) {
        ApiResponse response = foodService.updateFood(id, food);
        return ResponseEntity.ok(response);

    }

    @GetMapping("/food/{id}")
    public ResponseEntity<ApiResponse> getFood(@PathVariable Integer id) {
        ApiResponse response = foodService.getFoodDetail(id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/deactivate/{id}")
    public ResponseEntity<ApiResponse> deactivateFood(@PathVariable Integer id) {
        ApiResponse response = foodService.deactiveFood(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/foods")
    public ResponseEntity<ApiResponse> getFoods(@RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size,
                                                @RequestParam(required = false) String name,
                                                @RequestParam(required = false) String ingredients) {

        ApiResponse response = foodService.getFoodList(page, size, name, ingredients);
        return ResponseEntity.ok(response);
    }


}
