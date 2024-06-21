package ua.foxminded.javaspring.carmanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.shaded.com.github.dockerjava.core.MediaType;
import ua.foxminded.javaspring.carmanager.dto.BodyTypeDTO;
import ua.foxminded.javaspring.carmanager.dto.PaginateAndSort;
import ua.foxminded.javaspring.carmanager.entity.BodyType;
import ua.foxminded.javaspring.carmanager.service.BodyTypeService;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BodyTypeController.class)
public class BodyTypeControllerTest {

    @MockBean
    BodyTypeService service;

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @Test
    public void getAll_shouldReturnListOfBodyTypes() throws Exception {
        List<BodyType> bodyTypes = List.of(new BodyType("test"));
        when(service.getAll(new PaginateAndSort())).thenReturn(bodyTypes);
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/body_types"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("test"));
    }

    @Test
    public void getById_shouldReturnBodyType() throws Exception {
        when(service.get(0)).thenReturn(new BodyType());
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/body_types/{id}", 0))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(0));
    }

    @Test
    public void add_shouldReturnStatusBadRequest_whenRequestParametersNotValid() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/body_types")
                                          .contentType(MediaType.APPLICATION_JSON.getMediaType())
                                          .content(mapper.valueToTree(new BodyTypeDTO()).toString()))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void add_shouldReturnBodyType() throws Exception {
        BodyTypeDTO dto = new BodyTypeDTO();
        dto.setName("test");
        when(service.add(dto)).thenReturn(new BodyType());
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/body_types")
                                          .contentType(MediaType.APPLICATION_JSON.getMediaType())
                                          .content(mapper.valueToTree(dto).toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(0));
    }

    @Test
    public void update_shouldReturnStatusBadRequest_whenRequestParametersNotValid() throws Exception {
        mvc.perform(MockMvcRequestBuilders.put("/api/v1/body_types/{id}", 0)
                                          .contentType(MediaType.APPLICATION_JSON.getMediaType())
                                          .content(mapper.valueToTree(new BodyTypeDTO()).toString()))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void update_shouldReturnBodyType() throws Exception {
        BodyTypeDTO dto = new BodyTypeDTO();
        dto.setName("test");
        when(service.update(dto)).thenReturn(new BodyType());
        mvc.perform(MockMvcRequestBuilders.put("/api/v1/body_types/{id}", 0)
                                          .contentType(MediaType.APPLICATION_JSON.getMediaType())
                                          .content(mapper.valueToTree(dto).toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(0));
    }

    @Test
    public void delete_shouldReturnStatusOk() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/api/v1/body_types/{id}", 0))
                .andExpect(status().isOk());
    }
}
