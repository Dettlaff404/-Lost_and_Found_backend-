package lk.ijse.security.jwt;

import java.io.IOException;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.security.UserDetailService_IMPL;
import lombok.RequiredArgsConstructor;

@Configuration
@Component
@RequiredArgsConstructor
@Order(1)
public class AuthFilter extends OncePerRequestFilter {

    private final JWTUtils jwtUtils;
    private final UserDetailService_IMPL userDetailServiceIMPL;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            //get the token from the request
            String jwtToken = getJWTToken(request);

            if (jwtToken != null && jwtUtils.validateToken(jwtToken)) {
                String userName = jwtUtils.getUsernameFromToken(jwtToken);
                UserDetails userDetails = userDetailServiceIMPL.loadUserByUsername(userName);

                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                authToken.setDetails(new WebAuthenticationDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }

        }catch (Exception e) {
            e.printStackTrace();
        }

        filterChain.doFilter(request, response);
    }

    private String getJWTToken(HttpServletRequest request) {
        //get Auth header
        String authHeader = request.getHeader("Authorization");

        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }else {
            return null;
        }
    }
}
