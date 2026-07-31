package fu.de190913.gateway.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfig {
    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();

        // Cho phép tất cả các domain gọi tới (phù hợp khi code frontend bằng HTML/CSS chạy Live Server)
        corsConfig.addAllowedOriginPattern("*");

        // Cho phép tất cả các HTTP method (GET, POST, PUT, DELETE, OPTIONS...)
        corsConfig.addAllowedMethod("*");

        // Cho phép tất cả các loại Header (Authorization, Content-Type...)
        corsConfig.addAllowedHeader("*");

        // Cho phép gửi kèm cookie hoặc thông tin xác thực (rất quan trọng khi làm tính năng Login)
        corsConfig.setAllowCredentials(true);

        // Áp dụng cấu hình này cho mọi đường dẫn API đi qua Gateway
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);
    }
}
