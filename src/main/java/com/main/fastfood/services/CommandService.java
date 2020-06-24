package com.main.fastfood.services;

import com.main.fastfood.models.Command;
import com.main.fastfood.repositories.CommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CommandService {

    private final CommandRepository commandRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CommandService(CommandRepository commandRepository, PasswordEncoder passwordEncoder) {
        this.commandRepository = commandRepository;
        this.passwordEncoder = passwordEncoder;
    }


    // ******************************* All ******************************* //

    public List<Command> findAll() {
        List<Command> commands = this.commandRepository.findAll();
        if (commands.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot find the stream of commands");
        } else {
            return commands;
        }
    }

    // ****************************** Store ***************************** //

    public void store(Command command) {
        Optional<Command> existingCommand = Optional.ofNullable(this.commandRepository.findByLogin(command.getLogin()));

        // Password encryption
        command.setPassword(encodePassword(command.getPassword()));

        if (existingCommand.isEmpty()) {
            this.commandRepository.save(command);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Command already existing");
        }

    }

    // ************************** One | By Id ************************** //

    public Command findById(Integer id) {
        return this.commandRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot find the requested command")
                );
    }



    // ***************************** Update **************************** //

    public Command update(Integer id, Command command) {
        return this.commandRepository.findById(id)
                .map(currentCommand -> {
                    commandData(currentCommand, command); // Source => Destination
                    return this.commandRepository.save(currentCommand);
                })
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot find the requested command")
                );
    }

    // **************************** Destroy *************************** //

    public void destroy(Integer id) {
        Optional<Command> existingCommand = this.commandRepository.findById(id);

        if (existingCommand.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot find the requested command");
        } else {
            commandRepository.deleteById(id);
            // Or : commandRepository.findAll().remove(existingCommand.get());
        }
    }

    // ************************ MISCELLANEOUS ************************ //

    private void commandData(Command source, Command destination) {
        source.setLastName(destination.getLastName());
        source.setFirstName(destination.getFirstName());
        source.setLogin(destination.getLogin());
        source.setEmail(destination.getEmail());
        source.setAddress(destination.getAddress());
        source.setPostalCode(destination.getPostalCode());
        source.setStreet(destination.getStreet());
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
