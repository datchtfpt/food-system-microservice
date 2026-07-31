package fu.de190913.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * GatewayConfig - Cấu hình routing cho API Gateway bằng Java Code
 *
 * Đây là cách thay thế cho việc cấu hình trong application.yml.
 * Cả 2 cách đều hoạt động, nhưng dùng Java Code linh hoạt hơn
 * vì có thể thêm logic điều kiện phức tạp.
 *
 * Luồng hoạt động:
 *   Client → Gateway (8080) → [Route matching] → Service (8081/8082)
 */
@Configuration
public class GatewayConfig {

    /**
     * Đăng ký danh sách các route cho Gateway.
     * Mỗi route gồm 3 thành phần:
     *   - Predicate (.path): Điều kiện để route này được kích hoạt (dựa trên URL pattern)
     *   - Filter (.filters): Biến đổi request trước khi forward (ví dụ: cắt bỏ prefix URL)
     *   - URI (.uri): Địa chỉ thật của service sẽ nhận request
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()

                // =============================================
                // ROUTE 1: Restaurant Service
                // Request vào : http://localhost:8080/restaurant-service/api/restaurants
                // Forward đến : http://localhost:8081/api/restaurants  (cắt bỏ /restaurant-service)
                // =============================================
                .route("restaurant-service", r -> r
                        .path("/restaurant-service/**")  // Bắt tất cả URL bắt đầu bằng /restaurant-service/
                        .filters(f -> f
                                // rewritePath: dùng regex để cắt prefix /restaurant-service/
                                // (?<segment>.*) = capture group, lấy phần còn lại sau prefix
                                // /${segment}    = thay bằng phần đã capture (bỏ prefix đi)
                                .rewritePath("/restaurant-service/(?<segment>.*)", "/${segment}"))
                        .uri("http://localhost:8081"))   // Forward đến restaurant-service

                // =============================================
                // ROUTE 2: Food Service
                // Request vào : http://localhost:8080/food-service/api/foods
                // Forward đến : http://localhost:8082/api/foods  (cắt bỏ /food-service)
                // =============================================
                .route("food-service", r -> r
                        .path("/food-service/**")        // Bắt tất cả URL bắt đầu bằng /food-service/
                        .filters(f -> f
                                .rewritePath("/food-service/(?<segment>.*)", "/${segment}"))
                        .uri("http://localhost:8082"))   // Forward đến food-service

                .build();
    }
}
