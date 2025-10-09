package com.bruno.cadastrocliente.config;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bruno.cadastrocliente.model.ClienteModel;
import com.bruno.cadastrocliente.repository.ClienteRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private ClienteRepository clienteRepo;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            String userName = jwtUtil.extractLogin(token);

            Optional<ClienteModel> cliente = clienteRepo.findBycpf(userName);

            if (cliente.isPresent() && jwtUtil.validateToken(token, userName)) {
                // Aqui você pode armazenar o cliente no contexto se quiser
                request.setAttribute("clienteLogado", cliente.get());
            }
        }

        filterChain.doFilter(request, response);
    }
}

