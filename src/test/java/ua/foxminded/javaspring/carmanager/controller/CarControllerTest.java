package ua.foxminded.javaspring.carmanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ua.foxminded.javaspring.carmanager.configuration.SecurityConfig;
import ua.foxminded.javaspring.carmanager.dto.CarDTO;
import ua.foxminded.javaspring.carmanager.dto.PaginateAndSort;
import ua.foxminded.javaspring.carmanager.entity.BodyType;
import ua.foxminded.javaspring.carmanager.entity.Car;
import ua.foxminded.javaspring.carmanager.entity.Model;
import ua.foxminded.javaspring.carmanager.mapper.CarMapper;
import ua.foxminded.javaspring.carmanager.mapper.CarMapperImpl;
import ua.foxminded.javaspring.carmanager.service.CarService;

import java.time.Year;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarController.class)
@Import({CarMapperImpl.class, SecurityConfig.class})
public class CarControllerTest {

    @MockBean
    CarService service;

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    CarMapper carMapper;

    @Test
    @WithAnonymousUser
    public void getAll_shouldReturnListOfCars() throws Exception {
        Car car = new Car();
        car.setId("test");
        List<Car> cars = List.of(car);
        when(service.getAll(new PaginateAndSort())).thenReturn(cars);
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/cars"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("test"));
    }

    @Test
    @WithAnonymousUser
    public void getById_shouldReturnCar() throws Exception {
        Car car = new Car();
        car.setId("test");
        when(service.get("0")).thenReturn(car);
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/cars/{id}", 0))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("test"));
    }

    @Test
    @WithAnonymousUser
    public void add_shouldNotAllowAccessForUnauthorizedUser() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/cars")
                        .with(csrf()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    public void add_shouldReturnStatusBadRequest_whenRequestParametersNotValid() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content((new ObjectMapper()).valueToTree(new CarDTO()).toString())
                        .with(csrf()))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser
    public void add_shouldReturnCar() throws Exception {
        CarDTO dto = new CarDTO();
        dto.setId("test");
        dto.setModel(new Model());
        dto.setProductionYear(Year.now());
        dto.setBodyTypes(Set.of(new BodyType()));
        when(service.add(dto)).thenReturn(carMapper.dtoToCar(dto));
        mvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.valueToTree(dto).toString())
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("test"));
    }

    @Test
    @WithAnonymousUser
    public void update_shouldNotAllowAccessForUnauthorizedUser() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/cars/{id}", 0)
                        .with(csrf()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    public void update_shouldReturnStatusBadRequest_whenRequestParametersNotValid() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/cars/{id}", 0)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content((new ObjectMapper()).valueToTree(new CarDTO()).toString())
                        .with(csrf()))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser
    public void update_shouldReturnCar() throws Exception {
        CarDTO dto = new CarDTO();
        dto.setId("test");
        dto.setModel(new Model());
        dto.setProductionYear(Year.now());
        dto.setBodyTypes(Set.of(new BodyType()));
        when(service.update(dto)).thenReturn(carMapper.dtoToCar(dto));
        mvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/cars/{id}", "test")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.valueToTree(dto).toString())
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("test"));
    }

    @Test
    @WithAnonymousUser
    public void delete_shouldNotAllowAccessForUnauthorizedUser() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .delete("/api/v1/cars/{id}", 0)
                        .with(csrf()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    public void delete_shouldReturnStatusOk() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .delete("/api/v1/cars/{id}", 0)
                        .with(csrf()))
                .andExpect(status().isOk());
    }
}
