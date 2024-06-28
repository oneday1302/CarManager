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
import ua.foxminded.javaspring.carmanager.dto.BodyTypeDTO;
import ua.foxminded.javaspring.carmanager.dto.PaginateAndSort;
import ua.foxminded.javaspring.carmanager.entity.BodyType;
import ua.foxminded.javaspring.carmanager.service.BodyTypeService;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BodyTypeController.class)
@Import(SecurityConfig.class)
public class BodyTypeControllerTest {

    @MockBean
    BodyTypeService service;

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @Test
    @WithAnonymousUser
    public void getAll_shouldReturnListOfBodyTypes() throws Exception {
        List<BodyType> bodyTypes = List.of(new BodyType("test"));
        when(service.getAll(new PaginateAndSort())).thenReturn(bodyTypes);
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/body_types"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("test"));
    }

    @Test
    @WithAnonymousUser
    public void getById_shouldReturnBodyType() throws Exception {
        when(service.get(0)).thenReturn(new BodyType());
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/body_types/{id}", 0))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(0));
    }

    @Test
    @WithAnonymousUser
    public void add_shouldNotAllowAccessForUnauthorizedUser() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/body_types")
                        .with(csrf()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    public void add_shouldReturnStatusBadRequest_whenRequestParametersNotValid() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/body_types")
                        .contentType(MediaType.APPLICATION_JSON.getMediaType())
                        .content(mapper.valueToTree(new BodyTypeDTO()).toString())
                        .with(csrf()))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser
    public void add_shouldReturnBodyType() throws Exception {
        BodyTypeDTO dto = new BodyTypeDTO();
        dto.setName("test");
        when(service.add(dto)).thenReturn(new BodyType());
        mvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/body_types")
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
                        .put("/api/v1/body_types/{id}", 0)
                        .with(csrf()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    public void update_shouldReturnStatusBadRequest_whenRequestParametersNotValid() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/body_types/{id}", 0)
                        .contentType(MediaType.APPLICATION_JSON.getMediaType())
                        .content(mapper.valueToTree(new BodyTypeDTO()).toString())
                        .with(csrf()))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser
    public void update_shouldReturnBodyType() throws Exception {
        BodyTypeDTO dto = new BodyTypeDTO();
        dto.setName("test");
        when(service.update(dto)).thenReturn(new BodyType());
        mvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/body_types/{id}", 0)
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
                        .delete("/api/v1/body_types/{id}", 0)
                        .with(csrf()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    public void delete_shouldReturnStatusOk() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .delete("/api/v1/body_types/{id}", 0)
                        .with(csrf()))
                .andExpect(status().isOk());
    }
}
