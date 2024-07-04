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
import ua.foxminded.javaspring.carmanager.dto.BodyTypeDTO;
import ua.foxminded.javaspring.carmanager.dto.PaginateAndSort;
import ua.foxminded.javaspring.carmanager.entity.BodyType;
import ua.foxminded.javaspring.carmanager.service.BodyTypeService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/body_types")
@RequiredArgsConstructor
public class BodyTypeController {

    private final BodyTypeService service;

    @Operation(
            summary = "The method returns list of car body types and supports sorting and pagination.",
            responses = {
                    @ApiResponse(
                    description = "The method returns list of car body types.",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            description = "Parameters are not valid.",
                            responseCode = "400"
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<BodyType>> getAll(@Valid @ParameterObject PaginateAndSort paginateAndSort) {
        return ResponseEntity.ok(service.getAll(paginateAndSort));
    }

    @Operation(
            summary = "The method returns a car body type by id.",
            responses = {
                    @ApiResponse(
                            description = "The method returns a car body type.",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            description = "Entered id is not exist in database.",
                            responseCode = "404"
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<BodyType> getById(@PathVariable long id) {
        return ResponseEntity.ok(service.get(id));
    }

    @Operation(
            security = @SecurityRequirement(name = "bearerAuth"),
            summary = "The method add new body type to database and return added object.",
            responses = {
                    @ApiResponse(
                            description = "The method return added object.",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "401"
                    ),
                    @ApiResponse(
                            description = "Request body is not valid.",
                            responseCode = "400"
                    )
            }
    )
    @PostMapping
    public ResponseEntity<BodyType> add(@Valid @RequestBody BodyTypeDTO dto) {
        return ResponseEntity.ok(service.add(dto));
    }

    @Operation(
            security = @SecurityRequirement(name = "bearerAuth"),
            summary = "The method update a body type and save changes to database and return updated object.",
            responses = {
                    @ApiResponse(
                            description = "The method return updated object.",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            description = "Request body is not valid.",
                            responseCode = "400"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "401"
                    ),
                    @ApiResponse(
                            description = "Entered id is not exist in database.",
                            responseCode = "404"
                    )
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<BodyType> update(@PathVariable long id, @Valid @RequestBody BodyTypeDTO dto) {
        dto.setId(id);
        return ResponseEntity.ok(service.update(dto));
    }

    @Operation(
            security = @SecurityRequirement(name = "bearerAuth"),
            summary = "The method delete a body type from database.",
            responses = {
                    @ApiResponse(
                            description = "Object deleted from database.",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "401"
                    )
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.ok("Deleted Successfully");
    }
}
