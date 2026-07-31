package fa.training.foodservice.config;



import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                // Ép Swagger phải thêm tiền tố này vào mọi request khi test
                .addServersItem(new Server().url("/food-service").description("Gọi qua API Gateway"));
    }
}
