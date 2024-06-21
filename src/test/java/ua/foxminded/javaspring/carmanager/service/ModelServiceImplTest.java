package ua.foxminded.javaspring.carmanager.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.foxminded.javaspring.carmanager.config.ServiceTestConfig;
import ua.foxminded.javaspring.carmanager.dto.ModelDTO;
import ua.foxminded.javaspring.carmanager.entity.Model;
import ua.foxminded.javaspring.carmanager.repository.ModelRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest(classes = ServiceTestConfig.class)
public class ModelServiceImplTest {

    @Autowired
    ModelRepository repository;

    @Autowired
    ModelService service;

    @Test
    void update_shouldReturnIllegalArgumentException_whenInputParamNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.update(null);
        });
    }

    @Test
    void update_shouldUpdateModel() {
        ModelDTO dto = new ModelDTO();
        when(repository.findById(dto.getId())).thenReturn(Optional.of(new Model()));
        Model model = new Model();
        when(repository.save(model)).thenReturn(model);
        assertEquals(model, service.update(dto));
        verify(repository, times(1)).save(model);
    }
}
