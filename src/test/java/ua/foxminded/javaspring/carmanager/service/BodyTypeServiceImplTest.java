package ua.foxminded.javaspring.carmanager.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.foxminded.javaspring.carmanager.config.ServiceTestConfig;
import ua.foxminded.javaspring.carmanager.dto.BodyTypeDTO;
import ua.foxminded.javaspring.carmanager.entity.BodyType;
import ua.foxminded.javaspring.carmanager.repository.BodyTypeRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = ServiceTestConfig.class)
public class BodyTypeServiceImplTest {

    @Autowired
    BodyTypeRepository repository;

    @Autowired
    BodyTypeService service;

    @Test
    void update_shouldReturnIllegalArgumentException_whenInputParamNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.update(null);
        });
    }

    @Test
    void update_shouldUpdateBodyType() {
        BodyTypeDTO dto = new BodyTypeDTO();
        BodyType bodyType = new BodyType();
        when(repository.findById(dto.getId())).thenReturn(Optional.of(new BodyType()));
        when(repository.save(bodyType)).thenReturn(bodyType);
        assertEquals(bodyType, service.update(dto));
        verify(repository, times(1)).save(bodyType);
    }
}
