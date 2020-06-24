package com.main.fastfood.services;

import com.main.fastfood.models.Burger;
import com.main.fastfood.models.Command;
import com.main.fastfood.repositories.BurgerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class BurgerService {

    private final BurgerRepository burgerRepository;

    @Autowired
    public BurgerService(BurgerRepository burgerRepository) {
        this.burgerRepository = burgerRepository;
    }


    // ******************************* All ******************************* //

    public List<Burger> findAll() {
        List<Burger> burgers = this.burgerRepository.findAll();
        if (burgers.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot find the stream of burgers");
        } else {
            return burgers;
        }
    }

    // ****************************** Store ***************************** //

    public void store(Burger burger) {
        Optional<Burger> existingBurger = Optional.ofNullable(this.burgerRepository.findByName(burger.getName()));

        if (existingBurger.isEmpty()) {
            this.burgerRepository.save(burger);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Command already existing");
        }

    }

    // ************************** One | By Id ************************** //

    public Burger findById(Integer id) {
        return this.burgerRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot find the requested burger")
                );
    }

    // ***************************** Update **************************** //

    public Burger update(Integer id, Burger burger) {
        return this.burgerRepository.findById(id)
                .map(currentBurger -> {
                    burgerData(currentBurger, burger); // Source => Destination
                    return this.burgerRepository.save(currentBurger);
                })
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot find the requested burger")
                );
    }

    // **************************** Destroy *************************** //

    public void destroy(Integer id) {
        Optional<Burger> existingBurger = this.burgerRepository.findById(id);

        if (existingBurger.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot find the requested burger");
        } else {
            burgerRepository.deleteById(id);
            // Or : burgerRepository.findAll().remove(existingBurger.get());
        }
    }

    // ************************ MISCELLANEOUS ************************ //

    private void burgerData(Burger source, Burger destination) {
        source.setName(destination.getName());
        source.setPrice(destination.getPrice());
        source.setDescription(destination.getDescription());
        source.setRecipe(destination.getRecipe());
        source.setQuantity(destination.getQuantity());
    }
}
