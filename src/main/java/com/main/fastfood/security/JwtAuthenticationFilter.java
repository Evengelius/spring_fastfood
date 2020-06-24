package com.main.fastfood.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtProvider jwtProvider;

    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    // ***************************** Filter ***************************** //

    @Override
    protected void doFilterInternal
            (
                    HttpServletRequest httpServletRequest,
                    HttpServletResponse httpServletResponse,
                    FilterChain filterChain
            ) throws ServletException, IOException {

        // Retrieve the token from the request url
        String jwt = getJwtFromRequest(httpServletRequest);

        // If the token exist and is valid
        if(StringUtils.hasText(jwt) && jwtProvider.validToken(jwt)){

            // Get the username from the token
            String username = jwtProvider.getUsernameFromJWT(jwt);
            // Load the user
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            // Set a new instance of the authentication request with the data of the user about to be authenticated
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
                    null, userDetails.getAuthorities());
            // Stores additional details for the authentication request
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
            // Authenticate the user
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    // ********************** JWT | From Request *********************** //

    private String getJwtFromRequest(HttpServletRequest request) {

        /*
         * 1. Retrieve the header's token
         * 2. Check that the header exist and starts with "Bearer "
         * 3. Return the header's text starting from the seven position : so without "Bearer "
         */
        String bearerToken = request.getHeader("Authorization"); // 1.
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) { // 2.
            return bearerToken.substring(7); // 3.
        }
        return bearerToken;
    }
}
