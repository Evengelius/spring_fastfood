package com.main.fastfood.services;

import com.main.fastfood.mapping.AuthMapping;
import com.main.fastfood.models.User;
import com.main.fastfood.repositories.UserRepository;
import com.main.fastfood.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }

    // ******************************* All ******************************* //

    public List<User> findAll() {
        List<User> users = this.userRepository.findAll();
        if (users.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot find the stream of users");
        } else {
            return users;
        }
    }

    // ****************************** Store ***************************** //

    public void store(User user) {
        Optional<User> existingUser = this.userRepository.findByUsername(user.getUsername());

        // Password encryption
        user.setPassword(encodePassword(user.getPassword()));

        if (existingUser.isEmpty()) {
            this.userRepository.save(user);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already existing");
        }

    }

    // ****************************** Login ***************************** //

    public AuthenticationResponse login(AuthMapping authMapping) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authMapping.getUsername(),
                authMapping.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String authenticationToken = jwtProvider.generateToken(authenticate);
        return new AuthenticationResponse(authenticationToken, authMapping.getUsername());
    }

    // ************************** One | By Id ************************** //

    public User findById(Integer id) {
        return this.userRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot find the requested user")
                );
    }

    // ***************************** Update **************************** //

    public User update(Integer id, User user) {
        return this.userRepository.findById(id)
                .map(currentUser -> {
                    userData(currentUser, user); // Source => Destination
                    return this.userRepository.save(currentUser);
                })
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot find the requested user")
                );
    }

    // **************************** Destroy *************************** //

    public void destroy(Integer id) {
        Optional<User> existingUser = this.userRepository.findById(id);

        if (existingUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot find the requested user");
        } else {
            userRepository.deleteById(id);
            // Or : userRepository.findAll().remove(existingUser.get());
        }
    }

    // ************************ MISCELLANEOUS ************************ //

    private void userData(User source, User destination) {
        source.setName(destination.getName());
        source.setFirstname(destination.getFirstname());
        source.setUsername(destination.getUsername());
        source.setEmail(destination.getEmail());
        source.setPassword(encodePassword(destination.getPassword()));
    }

    private String encodePassword(String password) {
       return passwordEncoder.encode(password);
    }
}
