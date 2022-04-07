package pl.javastart.dnd;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnimalService {

    public static final long SORT_ORDER_STEP = 100L;
    private AnimalRepository animalRepository;

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    List<Animal> findAllSorted() {
        return animalRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Animal::getSortOrder))
                .collect(Collectors.toList());
    }

    void move(Long animalId, int targetPosition) {

        List<Animal> allAnimals = animalRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Animal::getSortOrder))
                .collect(Collectors.toList());

        Animal animal = animalRepository.findById(animalId).get();

        long targetSortOrder = calculateSortOrder(animal, allAnimals, targetPosition);
        animal.setSortOrder(targetSortOrder);
        animalRepository.save(animal);


    }

    long calculateSortOrder(Animal animal, List<Animal> animalList, int targetPosition) {

        animalList.remove(animal);

        if (targetPosition == 0) {
            Animal firstAnimalOnList = animalList.get(0);
            return firstAnimalOnList.getSortOrder() - SORT_ORDER_STEP;
        } else if (targetPosition == animalList.size()) {
            Animal lastAnimalOnList = animalList.get(animalList.size() - 1);
            return lastAnimalOnList.getSortOrder() + SORT_ORDER_STEP;
        } else {
            throw new RuntimeException("Not implemented");
        }
    }

    // TODO uzupełnij tę metodę oraz dodaj do niej testy, żeby sprawdzić czy działa

    // w ten sposób możesz pobrać wszystkie zwierzaki z bazy danych
    // List<Animal> allAnimals = animalRepository.findAll();

    // w ten sposób pobrac jednego zwierzaka po ID
    // Animal animal = animalRepository.findById(animalId).orElseThrow();

    // w ten sposób zapisać zmiany dotyczące zwierzaka do bazy
    // animal.setSortOrder(1000L);
    // animalRepository.save(animal);

    // w ten sposób zapisać zmiany w całej liście zwierzaków na raz
    // animalRepository.saveAll(allAnimals);

    // POWODZENIA!


}
