package pl.javastart.dnd;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class AnimalServiceTest {

    @Mock
    private AnimalRepository animalRepository;

    @InjectMocks
    private AnimalService animalService;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldCalculateForFirstPosition() {
        //given
        /*
            200 - Krowa
            300 - Kurczak
         */

        Animal animal1 = createAnimal(1L, "Krowa", 200L);
        Animal animal2 = createAnimal(2L, "Kurczak", 300L);

        List<Animal> animalList = new ArrayList<>(Arrays.asList(animal1, animal2));

        //when
        long sortOrder = animalService.calculateSortOrder(animal2, animalList, 0);

        //then
        Assertions.assertThat(sortOrder).isEqualTo(100L);
    }

    @Test
    public void shouldCalculateTheSameForFirstPosition() {
        //given
        Animal animal1 = createAnimal(1L, "Krowa", 200L);

        Animal animal2 = createAnimal(2L, "Kurczak", 300L);

        List<Animal> animalList = new ArrayList<>(Arrays.asList(animal1, animal2));

        //when
        long sortOrder = animalService.calculateSortOrder(animal1, animalList, 0);

        //then
        Assertions.assertThat(sortOrder).isEqualTo(200L);
    }

    @Test
    public void shouldCalculateForLastPosition() {
        //given
        /*
            400 - Krowa
            300 - Kurczak
         */

        Animal animal1 = createAnimal(1L, "Krowa", 200L);

        Animal animal2 = createAnimal(2L, "Kurczak", 300L);

        List<Animal> animalList = new ArrayList<>(Arrays.asList(animal1, animal2));

        //when
        long sortOrder = animalService.calculateSortOrder(animal1, animalList, 1);

        //then
        Assertions.assertThat(sortOrder).isEqualTo(400L);
    }

    @Test
    public void shouldCalculateTheSameSortOrderForLastPosition() {
        //given
        /*
            400 - Krowa
            300 - Kurczak
         */

        Animal animal1 = createAnimal(1L, "Krowa", 200L);

        Animal animal2 = createAnimal(2L, "Kurczak", 300L);

        List<Animal> animalList = new ArrayList<>(Arrays.asList(animal1, animal2));

        //when
        long sortOrder = animalService.calculateSortOrder(animal2, animalList, 1);

        //then
        Assertions.assertThat(sortOrder).isEqualTo(300L);
    }

    @Test
    public void shouldCalculateTheSameSortOrderForLastPosition() {
        //given
        /*
            400 - Krowa
            300 - Kurczak
         */

        Animal animal1 = createAnimal(1L, "Krowa", 200L);
        Animal animal2 = createAnimal(2L, "Kurczak", 300L);
        Animal animal3 = createAnimal(3L, "Pies", 400L);

        List<Animal> animalList = new ArrayList<>(Arrays.asList(animal1, animal2));

        //when
        long sortOrder = animalService.calculateSortOrder(animal1, animalList, 1);

        //then
        Assertions.assertThat(sortOrder).isEqualTo(300L);
    }


    private Animal createAnimal(long id, String name, long sortOrder) {
        Animal animal1 = new Animal();
        animal1.setId(id);
        animal1.setName(name);
        animal1.setSortOrder(sortOrder);
        return animal1;
    }

}