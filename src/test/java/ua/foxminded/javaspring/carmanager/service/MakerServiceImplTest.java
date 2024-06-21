package ua.foxminded.javaspring.carmanager.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.foxminded.javaspring.carmanager.config.ServiceTestConfig;
import ua.foxminded.javaspring.carmanager.dto.MakerDTO;
import ua.foxminded.javaspring.carmanager.entity.Maker;
import ua.foxminded.javaspring.carmanager.repository.MakerRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest(classes = ServiceTestConfig.class)
public class MakerServiceImplTest {

    @Autowired
    MakerRepository repository;

    @Autowired
    MakerService service;

    @Test
    void update_shouldReturnIllegalArgumentException_whenInputParamNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.update(null);
        });
    }

    @Test
    void update_shouldUpdateMaker() {
        MakerDTO dto = new MakerDTO();
        Maker maker = new Maker();
        when(repository.findById(dto.getId())).thenReturn(Optional.of(new Maker()));
        when(repository.save(maker)).thenReturn(maker);
        assertEquals(maker, service.update(dto));
        verify(repository, times(1)).save(maker);
    }
}
