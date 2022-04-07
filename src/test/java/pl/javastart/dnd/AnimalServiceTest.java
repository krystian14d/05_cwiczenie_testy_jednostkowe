package pl.javastart.dnd;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AnimalServiceTest {

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


        Animal animal1 = new Animal();
        animal1.setId(1L);
        animal1.setName("Kurczak");
        animal1.setSortOrder(300L);

        when(animalRepository.findById(1L)).thenReturn(Optional.of(animal1));

        Animal animal2 = new Animal();
        animal2.setId(2L);
        animal2.setName("Krowa");
        animal2.setSortOrder(200L);

        when(animalRepository.findById(2L)).thenReturn(Optional.of(animal2));

        when(animalRepository.findAll()).thenReturn(Arrays.asList(animal1, animal2));

        //when
        animalService.move(1L, 0);

        //then

        verify(animalRepository, times(1)).save(animalArgumentCaptor.capture());
        Animal animalSavedToDb = animalArgumentCaptor.getValue();

        Assertions.assertThat(animalSavedToDb.getSortOrder()).isEqualTo(100L);
    }

    @Test
    public void shouldCalculateForLastPosition() {
        //given
        MockitoAnnotations.openMocks(this);
        /*
            400 - Krowa
            300 - Kurczak
         */


        Animal animal1 = new Animal();
        animal1.setId(1L);
        animal1.setName("Kurczak");
        animal1.setSortOrder(300L);

        when(animalRepository.findById(1L)).thenReturn(Optional.of(animal1));

        Animal animal2 = new Animal();
        animal2.setId(2L);
        animal2.setName("Krowa");
        animal2.setSortOrder(200L);

        when(animalRepository.findById(2L)).thenReturn(Optional.of(animal2));

        when(animalRepository.findAll()).thenReturn(Arrays.asList(animal1, animal2));

        //when
        animalService.move(1L, 1);

        //then

        verify(animalRepository, times(1)).save(animalArgumentCaptor.capture());
        Animal animalSavedToDb = animalArgumentCaptor.getValue();

        Assertions.assertThat(animalSavedToDb.getSortOrder()).isEqualTo(400L);
    }
}