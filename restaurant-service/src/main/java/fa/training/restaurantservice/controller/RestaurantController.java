package fa.training.restaurantservice.controller;


import fa.training.restaurantservice.dto.ApiResponse;
import fa.training.restaurantservice.dto.RestaurantDTO;
import fa.training.restaurantservice.service.RestaurantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping("/restaurants")
    public ResponseEntity<ApiResponse> create(@RequestBody RestaurantDTO restaurant) {

        ApiResponse apiResponse = restaurantService.create(restaurant);
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/restaurants/{restaurantId}")
    public ResponseEntity<ApiResponse> update(@PathVariable Integer restaurantId, @RequestBody RestaurantDTO restaurant) {
        ApiResponse response = restaurantService.update(restaurantId, restaurant);
        return ResponseEntity.ok(response);

    }

    @GetMapping("/restaurants/{restaurantId}")
    public ResponseEntity<ApiResponse> getFood(@PathVariable Integer restaurantId) {
        ApiResponse response = restaurantService.getDetail(restaurantId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/restaurants/{restaurantId}")
    public ResponseEntity<ApiResponse> deactivate(@PathVariable Integer restaurantId) {
        ApiResponse response = restaurantService.deactivate(restaurantId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/restaurants")
    public ResponseEntity<ApiResponse> getList(@RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size,
                                                @RequestParam(required = false) String name,
                                                @RequestParam(required = false) String ownerName) {

        ApiResponse response = restaurantService.getList(page, size, name, ownerName);
        return ResponseEntity.ok(response);
    }


}
