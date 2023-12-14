package ru.task.taskmanagementsystem.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.task.taskmanagementsystem.dto.RegisterRequest;
import ru.task.taskmanagementsystem.dto.AuthenticationRequest;
import ru.task.taskmanagementsystem.dto.AuthenticationResponse;
import ru.task.taskmanagementsystem.entity.Role;
import ru.task.taskmanagementsystem.entity.User;
import ru.task.taskmanagementsystem.exception.RegistrationException;
import ru.task.taskmanagementsystem.repository.UserRepository;

import java.util.Optional;

@Service
@Slf4j
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                                 JwtService jwtService, AuthenticationManager authManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authManager = authManager;
    }

    public AuthenticationResponse register(RegisterRequest request) {
        log.info("Got registerRequest: {}", request); // todo danger: open password

        Optional<User> foundUser = userRepository.findByEmail(request.getEmail());
        if (foundUser.isPresent()) {
            throw new RegistrationException(String.format("Пользователь с email: %s уже зарегистрирован",
                    foundUser.get().getUsername())
            );
        }
        var user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .name(request.getName())
                .build();
        userRepository.save(user);
        log.info("Saved user: {}", user);

        var jwtToken = jwtService.generateToken(user);
        log.info("Generated user's token: {}", jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .role(user.getRole().getName())
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        log.info("Got authenticationRequest: {}", request);
        authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("Пользователь с email: %s не найден", request.getEmail())));
        log.info("Found user: {}", user);

        var jwtToken = jwtService.generateToken(user);
        log.info("Generated user's token: {}", jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .role(user.getRole().name())
                .build();
    }
}
