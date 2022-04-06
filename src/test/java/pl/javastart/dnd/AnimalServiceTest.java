package pl.javastart.dnd;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AnimalServiceTest {

    //skończony film na 11min

    @Mock
    private AnimalRepository animalRepository;

    @InjectMocks
    private AnimalService animalService;

    @Captor
    ArgumentCaptor<Animal> animalArgumentCaptor;

    @Test
    public void shouldCalculateForFirstPosition() {
        //given
        MockitoAnnotations.openMocks(this);
        /*
            200 - Krowa
            300 - Kurczak
         */


        Animal animal = new Animal();
        animal.setId(1L);
        animal.setName("Kurczak");
        animal.setSortOrder(300L);

        Mockito.when(animalRepository.findById(1L)).thenReturn(Optional.of(animal));

        Animal animal2 = new Animal();
        animal2.setId(2L);
        animal2.setName("Krowa");
        animal2.setSortOrder(200L);

        Mockito.when(animalRepository.findById(2L)).thenReturn(Optional.of(animal2));

        //when
        animalService.move(1L, 0);

        //then

        Mockito.verify(animalRepository, Mockito.times(1)).save(animalArgumentCaptor.capture());
        Animal animalSavedToDb = animalArgumentCaptor.getValue();

        Assertions.assertThat(animalSavedToDb.getSortOrder()).isEqualTo(-100L);


    }
}