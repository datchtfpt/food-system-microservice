package fa.training.foodservice.config;

import fa.training.foodservice.dto.UserDTO;
import fa.training.foodservice.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthFilter extends OncePerRequestFilter {

    private JwtUtil jwtUtil;

    private UserDetailsService userDetailsService;

    public JwtAuthFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
//
//        final String header = request.getHeader("Authorization");
//        final String jwt;
//        final String username;
//
//        if(header == null || !header.startsWith("Bearer ")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        jwt = header.substring(7);
//        try{
//            username =  jwtUtil.extractUsername(jwt);
//        }catch (Exception e){
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//
//
//
//
    }
}
