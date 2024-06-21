package ua.foxminded.javaspring.carmanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.shaded.com.github.dockerjava.core.MediaType;
import ua.foxminded.javaspring.carmanager.dto.MakerDTO;
import ua.foxminded.javaspring.carmanager.dto.PaginateAndSort;
import ua.foxminded.javaspring.carmanager.entity.Maker;
import ua.foxminded.javaspring.carmanager.service.MakerService;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MakerController.class)
public class MakerControllerTest {

    @MockBean
    MakerService service;

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @Test
    public void getAll_shouldReturnListOfMakers() throws Exception {
        List<Maker> makers = List.of(new Maker("test"));
        when(service.getAll(new PaginateAndSort())).thenReturn(makers);
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/makers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("test"));
    }

    @Test
    public void getById_shouldReturnMaker() throws Exception {
        when(service.get(0)).thenReturn(new Maker());
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/makers/{id}", 0))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(0));
    }

    @Test
    public void add_shouldReturnStatusBadRequest_whenRequestParametersNotValid() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/makers")
                                          .contentType(MediaType.APPLICATION_JSON.getMediaType())
                                          .content(mapper.valueToTree(new MakerDTO()).toString()))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void add_shouldReturnMaker() throws Exception {
        MakerDTO dto = new MakerDTO();
        dto.setName("test");
        when(service.add(dto)).thenReturn(new Maker());
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/makers")
                                          .contentType(MediaType.APPLICATION_JSON.getMediaType())
                                          .content(mapper.valueToTree(dto).toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(0));
    }

    @Test
    public void update_shouldReturnStatusBadRequest_whenRequestParametersNotValid() throws Exception {
        mvc.perform(MockMvcRequestBuilders.put("/api/v1/makers/{id}", 0)
                                          .contentType(MediaType.APPLICATION_JSON.getMediaType())
                                          .content(mapper.valueToTree(new MakerDTO()).toString()))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void update_shouldReturnMaker() throws Exception {
        MakerDTO dto = new MakerDTO();
        dto.setName("test");
        when(service.update(dto)).thenReturn(new Maker());
        mvc.perform(MockMvcRequestBuilders.put("/api/v1/makers/{id}", 0)
                                          .contentType(MediaType.APPLICATION_JSON.getMediaType())
                                          .content(mapper.valueToTree(dto).toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(0));
    }

    @Test
    public void delete_shouldReturnStatusOk() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/api/v1/makers/{id}", 0))
                .andExpect(status().isOk());
    }
}
