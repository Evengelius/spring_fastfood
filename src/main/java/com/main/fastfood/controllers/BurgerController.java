package com.main.fastfood.controllers;

import com.main.fastfood.mapper.BurgerMapper;
import com.main.fastfood.mapping.BurgerMapping;
import com.main.fastfood.mapping.CommandMapping;
import com.main.fastfood.models.Burger;
import com.main.fastfood.models.Command;
import com.main.fastfood.services.BurgerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/api/burgers")
public class BurgerController {

    private final BurgerMapper burgerMapper;
    private final BurgerService burgerService;

    @Autowired
    public BurgerController(BurgerMapper burgerMapper, BurgerService burgerService) {
        this.burgerMapper = burgerMapper;
        this.burgerService = burgerService;
    }


    // ******************************* GET ******************************* //

    // Burger  All
    @GetMapping
    public List<BurgerMapping> findAll() {
        return this.burgerMapper.entityListToDto(burgerService.findAll());
    }

    // Burger | Find One | By Id
    @GetMapping("/{id}")
    public BurgerMapping findById(@PathVariable @Positive Integer id) {
        return this.burgerMapper.entityToDto(this.burgerService.findById(id));

    }

    // ****************************** POST ****************************** //

    // Burger | Store
    @PostMapping
    /*
     * @Valid tells Spring Boot to automatically instantiate a Validator, and to validate the object : Burger here.
     * This check is performed before the method body is executed.
     * If the validation fails, the method will throw a MethodArgumentNotValidException, which is mapped to the 400 Bad Request response status by default
     */
    public ResponseEntity<Void> create(@Valid @RequestBody Burger burger) {
        this.burgerService.store(burger);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // ******************************* PUT ******************************* //

    // Burger | Update
    @PutMapping("/{id}")
    public BurgerMapping edit(@PathVariable Integer id, @RequestBody Burger burger) {
        return this.burgerMapper.entityToDto(this.burgerService.update(id, burger));
    }

    // ***************************** DELETE ***************************** //

    // Command | Destroy
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        this.burgerService.destroy(id);
    }
}
