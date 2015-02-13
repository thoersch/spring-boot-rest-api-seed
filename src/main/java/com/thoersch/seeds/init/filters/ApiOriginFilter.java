package com.thoersch.seeds.init.filters;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ApiOriginFilter extends OncePerRequestFilter {
    private static final String MAX_PRE_FLIGHT_CACHE_SECONDS = String.valueOf(60 * 60 * 24);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        response.addHeader("Cache-Control", "no-cache");

        if (request.getHeader("Access-Control-Request-Method") != null && request.getMethod().equals("OPTIONS")) {
            // CORS "pre-flight" request
            response.addHeader("Access-Control-Allow-Credentials", "true");
            response.addHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS, DELETE, PUT, PATCH, HEAD");
            response.addHeader("Access-Control-Allow-Headers", "Origin, Content-Type, api_key, Accept, Authorization, X-Requested-With");
            response.addHeader("Access-Control-Max-Age", MAX_PRE_FLIGHT_CACHE_SECONDS);
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
