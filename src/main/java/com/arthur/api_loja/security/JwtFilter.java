package com.arthur.api_loja.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtFilter implements Filter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        String header = req.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {

            String token = header.substring(7);

            try {
                var claims = jwtUtil.validarToken(token);

                req.setAttribute("email", claims.getSubject());
                req.setAttribute("role", claims.get("role"));

            } catch (Exception e) {
                ((HttpServletResponse) response).sendError(401, "Token inválido");
                return;
            }
        }

        chain.doFilter(request, response);
    }
}