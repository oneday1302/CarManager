package ua.foxminded.javaspring.carmanager.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.foxminded.javaspring.carmanager.dto.CarDTO;
import ua.foxminded.javaspring.carmanager.dto.PaginateAndSort;
import ua.foxminded.javaspring.carmanager.entity.Car;
import ua.foxminded.javaspring.carmanager.service.CarService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService service;

    @Operation(
            summary = "This endpoint available for all users.",
            description = "This endpoint available for all users. The method returns list of cars and supports sorting and pagination.",
            responses = @ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json")
            )
    )
    @GetMapping
    public ResponseEntity<List<Car>> getAll(@Valid @ParameterObject PaginateAndSort paginateAndSort) {
        return ResponseEntity.ok(service.getAll(paginateAndSort));
    }

    @Operation(
            summary = "This endpoint available for all users.",
            description = "This endpoint available for all users. The method returns a car by id.",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            description = "Not Found",
                            responseCode = "404"
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Car> getById(@PathVariable String id) {
        return ResponseEntity.ok(service.get(id));
    }

    @Operation(
            security = @SecurityRequirement(name = "bearerAuth"),
            summary = "This endpoint for authorized users.",
            description = "This endpoint for authorized users. The method add new car to database and return added object.",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "401"
                    ),
                    @ApiResponse(
                            description = "Not Valid",
                            responseCode = "400"
                    )
            }
    )
    @PostMapping
    public ResponseEntity<Car> add(@Valid @RequestBody CarDTO dto) {
        return ResponseEntity.ok(service.add(dto));
    }

    @Operation(
            security = @SecurityRequirement(name = "bearerAuth"),
            summary = "This endpoint for authorized users.",
            description = "This endpoint for authorized users. The method update a car and save changes to database and return updated object.",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "401"
                    ),
                    @ApiResponse(
                            description = "Not Valid",
                            responseCode = "400"
                    )
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<Car> update(@PathVariable String id, @Valid @RequestBody CarDTO dto) {
        dto.setId(id);
        return ResponseEntity.ok(service.update(dto));
    }

    @Operation(
            security = @SecurityRequirement(name = "bearerAuth"),
            summary = "This endpoint for authorized users.",
            description = "This endpoint for authorized users. The method delete a car from database.",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "401"
                    )
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.ok("Deleted Successfully");
    }
}
