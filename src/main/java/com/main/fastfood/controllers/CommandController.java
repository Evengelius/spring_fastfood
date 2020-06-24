package com.main.fastfood.controllers;

import com.main.fastfood.mapper.CommandMapper;
import com.main.fastfood.mapping.CommandMapping;
import com.main.fastfood.models.Command;
import com.main.fastfood.services.CommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/api/commands")
public class CommandController {

    private final CommandMapper commandMapper;
    private final CommandService commandService;

    @Autowired
    public CommandController(CommandMapper commandMapper, CommandService commandService) {
        this.commandMapper = commandMapper;
        this.commandService = commandService;
    }

    // ******************************* GET ******************************* //

    // Command  All
    @GetMapping
    public List<CommandMapping> findAll() {
        return this.commandMapper.entityListToDto(commandService.findAll());
    }

    // Command | Find One | By Id
    @GetMapping("/{id}")
    public CommandMapping findById(@PathVariable @Positive Integer id) {
        return this.commandMapper.entityToDto(this.commandService.findById(id));

    }

    // ****************************** POST ****************************** //

    // Command | Store
    @PostMapping
    /*
     * @Valid tells Spring Boot to automatically instantiate a Validator, and to validate the object : Command here.
     * This check is performed before the method body is executed.
     * If the validation fails, the method will throw a MethodArgumentNotValidException,
     * which is mapped to the 400 Bad Request response status by default
     */
    public ResponseEntity<Void> create(@Valid @RequestBody Command command) {
        this.commandService.store(command);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // ******************************* PUT ******************************* //

    // Burger | Update
    @PutMapping("/{id}")
    public CommandMapping edit(@PathVariable Integer id, @RequestBody Command command) {
        return this.commandMapper.entityToDto(this.commandService.update(id, command));
    }

    // ***************************** DELETE ***************************** //

    // Command | Destroy
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        this.commandService.destroy(id);
    }
}
