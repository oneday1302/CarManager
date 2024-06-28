package ua.foxminded.javaspring.carmanager.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.foxminded.javaspring.carmanager.configuration.ServiceTestConfig;
import ua.foxminded.javaspring.carmanager.dto.CarDTO;
import ua.foxminded.javaspring.carmanager.entity.Car;
import ua.foxminded.javaspring.carmanager.repository.CarRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = ServiceTestConfig.class)
public class CarServiceImplTest {

    @Autowired
    CarRepository repository;

    @Autowired
    CarService service;

    @Test
    void update_shouldReturnIllegalArgumentException_whenInputParamNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.update(null);
        });
    }

    @Test
    void update_shouldUpdateCar() {
        CarDTO dto = new CarDTO();
        dto.setId("test");
        Car car = new Car();
        when(repository.findById(dto.getId())).thenReturn(Optional.of(new Car()));
        when(repository.save(car)).thenReturn(car);
        assertEquals(car, service.update(dto));
        verify(repository, times(1)).save(car);
    }
}
