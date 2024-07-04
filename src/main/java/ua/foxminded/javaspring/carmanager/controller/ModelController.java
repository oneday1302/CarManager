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
import ua.foxminded.javaspring.carmanager.dto.ModelDTO;
import ua.foxminded.javaspring.carmanager.dto.PaginateAndSort;
import ua.foxminded.javaspring.carmanager.entity.Model;
import ua.foxminded.javaspring.carmanager.service.ModelService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/models")
@RequiredArgsConstructor
public class ModelController {

    private final ModelService service;

    @Operation(
            summary = "The method returns list of models and supports sorting and pagination.",
            responses = {
                    @ApiResponse(
                            description = "The method returns list of models.",
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
    public ResponseEntity<List<Model>> getAll(@Valid @ParameterObject PaginateAndSort paginateAndSort) {
        return ResponseEntity.ok(service.getAll(paginateAndSort));
    }

    @Operation(
            summary = "The method returns a model by id.",
            responses = {
                    @ApiResponse(
                            description = "The method returns a model.",
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
    public ResponseEntity<Model> getById(@PathVariable long id) {
        return ResponseEntity.ok(service.get(id));
    }

    @Operation(
            security = @SecurityRequirement(name = "bearerAuth"),
            summary = "The method add new model to database and return added object.",
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
    public ResponseEntity<Model> add(@Valid @RequestBody ModelDTO dto) {
        return ResponseEntity.ok(service.add(dto));
    }

    @Operation(
            security = @SecurityRequirement(name = "bearerAuth"),
            summary = "The method update a model and save changes to database and return updated object.",
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
    public ResponseEntity<Model> update(@PathVariable long id, @Valid @RequestBody ModelDTO dto) {
        dto.setId(id);
        return ResponseEntity.ok(service.update(dto));
    }

    @Operation(
            security = @SecurityRequirement(name = "bearerAuth"),
            summary = "The method delete a model from database.",
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
