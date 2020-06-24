package com.main.fastfood.controllers;

import com.main.fastfood.mapper.UserMapper;
import com.main.fastfood.mapping.AuthMapping;
import com.main.fastfood.mapping.BurgerMapping;
import com.main.fastfood.mapping.UserMapping;
import com.main.fastfood.models.Burger;
import com.main.fastfood.models.User;
import com.main.fastfood.services.AuthenticationResponse;
import com.main.fastfood.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserMapper userMapper;
    private final UserService userService;

    @Autowired
    public UserController(UserMapper userMapper, UserService userService) {
        this.userMapper = userMapper;
        this.userService = userService;
    }

    // ******************************* GET ******************************* //

    // User  All
    @GetMapping
    public List<UserMapping> findAll() {
        return this.userMapper.entityListToDto(userService.findAll());
    }

    // User | Find One | By Id
    @GetMapping("/{id}")
    public UserMapping findById(@PathVariable @Positive Integer id) {
        return this.userMapper.entityToDto(this.userService.findById(id));

    }

    // ****************************** POST ****************************** //

    // User | Store
    @PostMapping("/register")
    /*
     * @Valid tells Spring Boot to automatically instantiate a Validator, and to validate the object : User here.
     * This check is performed before the method body is executed.
     * If the validation fails, the method will throw a MethodArgumentNotValidException, which is mapped to the 400 Bad Request response status by default
     */
    public ResponseEntity<Void> create(@Valid @RequestBody User user) {
        this.userService.store(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // User | Login
    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody AuthMapping authMapping) {
        return userService.login(authMapping);
    }

    // ******************************* PUT ******************************* //

    // User | Update
    @PutMapping("/{id}")
    public UserMapping edit(@PathVariable Integer id, @RequestBody User user) {
        return this.userMapper.entityToDto(this.userService.update(id, user));
    }

    // ***************************** DELETE ***************************** //

    // User | Destroy
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        this.userService.destroy(id);
    }
}
