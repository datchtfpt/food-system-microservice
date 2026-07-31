package fa.training.restaurantservice.config;



import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                // Ép Swagger phải thêm tiền tố này vào mọi request khi test
                .addServersItem(new Server().url("/restaurant-service").description("Gọi qua API Gateway"));
    }
}
