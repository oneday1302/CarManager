package ua.foxminded.javaspring.carmanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.shaded.com.github.dockerjava.core.MediaType;
import ua.foxminded.javaspring.carmanager.configuration.SecurityConfig;
import ua.foxminded.javaspring.carmanager.dto.ModelDTO;
import ua.foxminded.javaspring.carmanager.dto.PaginateAndSort;
import ua.foxminded.javaspring.carmanager.entity.Maker;
import ua.foxminded.javaspring.carmanager.entity.Model;
import ua.foxminded.javaspring.carmanager.service.ModelService;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ModelController.class)
@Import(SecurityConfig.class)
public class ModelControllerTest {

    @MockBean
    ModelService service;

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @Test
    @WithAnonymousUser
    public void getAll_shouldReturnListOfModels() throws Exception {
        List<Model> models = List.of(new Model());
        when(service.getAll(new PaginateAndSort())).thenReturn(models);
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/models"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(0));
    }

    @Test
    @WithAnonymousUser
    public void getById_shouldReturnModel() throws Exception {
        when(service.get(0)).thenReturn(new Model());
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/models/{id}", 0))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(0));
    }

    @Test
    @WithAnonymousUser
    public void add_shouldNotAllowAccessForUnauthorizedUser() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/models")
                        .with(csrf()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    public void add_shouldReturnStatusBadRequest_whenRequestParametersNotValid() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/models")
                        .contentType(MediaType.APPLICATION_JSON.getMediaType())
                        .content(mapper.valueToTree(new ModelDTO()).toString())
                        .with(csrf()))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser
    public void add_shouldReturnModel() throws Exception {
        ModelDTO dto = new ModelDTO();
        dto.setMaker(new Maker());
        dto.setName("test");
        when(service.add(dto)).thenReturn(new Model());
        mvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/models")
                        .contentType(MediaType.APPLICATION_JSON.getMediaType())
                        .content(mapper.valueToTree(dto).toString())
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(0));
    }

    @Test
    @WithAnonymousUser
    public void update_shouldNotAllowAccessForUnauthorizedUser() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/models/{id}", 0)
                        .with(csrf()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    public void update_shouldReturnStatusBadRequest_whenRequestParametersNotValid() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/models/{id}", 0)
                        .contentType(MediaType.APPLICATION_JSON.getMediaType())
                        .content(mapper.valueToTree(new ModelDTO()).toString())
                        .with(csrf()))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser
    public void update_shouldReturnModel() throws Exception {
        ModelDTO dto = new ModelDTO();
        dto.setMaker(new Maker());
        dto.setName("test");
        when(service.update(dto)).thenReturn(new Model());
        mvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/models/{id}", 0)
                        .contentType(MediaType.APPLICATION_JSON.getMediaType())
                        .content(mapper.valueToTree(dto).toString())
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(0));
    }

    @Test
    @WithAnonymousUser
    public void delete_shouldNotAllowAccessForUnauthorizedUser() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .delete("/api/v1/models/{id}", 0)
                        .with(csrf()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    public void delete_shouldReturnStatusOk() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .delete("/api/v1/models/{id}", 0)
                        .with(csrf()))
                .andExpect(status().isOk());
    }
}
