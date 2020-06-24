package com.main.fastfood.services;

import com.main.fastfood.models.Drink;
import com.main.fastfood.repositories.DrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class DrinkService {

    private final DrinkRepository drinkRepository;

    @Autowired
    public DrinkService(DrinkRepository drinkRepository) {
        this.drinkRepository = drinkRepository;
    }

    // ******************************* All ******************************* //

    public List<Drink> findAll() {
        List<Drink> drinks = this.drinkRepository.findAll();
        if (drinks.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot find the stream of drinks");
        } else {
            return drinks;
        }
    }

    // ****************************** Store ***************************** //

    public void store(Drink drink) {
        Optional<Drink> existingDrink = Optional.ofNullable(this.drinkRepository.findByName(drink.getName()));

        if (existingDrink.isEmpty()) {
            this.drinkRepository.save(drink);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Drink already existing");
        }

    }

    // ************************** One | By Id ************************** //

    public Drink findById(Integer id) {
        return this.drinkRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot find the requested drink")
                );
    }

    // ***************************** Update **************************** //

    public Drink update(Integer id, Drink drink) {
        return this.drinkRepository.findById(id)
                .map(currentDrink -> {
                    drinkData(currentDrink, drink); // Source => Destination
                    return this.drinkRepository.save(currentDrink);
                })
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot find the requested drink")
                );
    }

    // **************************** Destroy *************************** //

    public void destroy(Integer id) {
        Optional<Drink> existingDrink = this.drinkRepository.findById(id);

        if (existingDrink.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot find the requested drink");
        } else {
            drinkRepository.deleteById(id);
            // Or : drinkRepository.findAll().remove(existingDrink.get());
        }
    }

    // ************************ MISCELLANEOUS ************************ //

    private void drinkData(Drink source, Drink destination) {
        source.setName(destination.getName());
        source.setPrice(destination.getPrice());
        source.setDescription(destination.getDescription());
        source.setCalories(destination.getCalories());
        source.setQuantity(destination.getQuantity());
    }
}
