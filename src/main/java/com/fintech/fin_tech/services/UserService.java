package com.fintech.fin_tech.services;

import com.fintech.fin_tech.dto.AuthResponse;
import com.fintech.fin_tech.dto.LoginRequest;
import com.fintech.fin_tech.dto.RegisterRequest;
import com.fintech.fin_tech.model.User;
import com.fintech.fin_tech.repository.UserRepository;
import com.fintech.fin_tech.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Kullanıcı adı zaten alınmış.");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("E-posta zaten kayıtlı.");
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userRepository.save(user);

        String token = jwtUtil.generateToken(user.getUsername());

        return new AuthResponse(token);
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı."));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Şifre hatalı.");
        }

        String token = jwtUtil.generateToken(user.getUsername());

        return new AuthResponse(token);
    }
}
