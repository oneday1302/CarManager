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
import ua.foxminded.javaspring.carmanager.dto.MakerDTO;
import ua.foxminded.javaspring.carmanager.dto.PaginateAndSort;
import ua.foxminded.javaspring.carmanager.entity.Maker;
import ua.foxminded.javaspring.carmanager.service.MakerService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/makers")
@RequiredArgsConstructor
public class MakerController {

    private final MakerService service;

    @Operation(
            summary = "This endpoint available for all users.",
            description = "This endpoint available for all users. The method returns list of makers and supports sorting and pagination.",
            responses = @ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json")
            )
    )
    @GetMapping
    public ResponseEntity<List<Maker>> getAll(@Valid @ParameterObject PaginateAndSort paginateAndSort) {
        return ResponseEntity.ok(service.getAll(paginateAndSort));
    }

    @Operation(
            summary = "This endpoint available for all users.",
            description = "This endpoint available for all users. The method returns a maker by id.",
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
    public ResponseEntity<Maker> getById(@PathVariable long id) {
        return ResponseEntity.ok(service.get(id));
    }

    @Operation(
            security = @SecurityRequirement(name = "bearerAuth"),
            summary = "This endpoint for authorized users.",
            description = "This endpoint for authorized users. The method add new maker to database and return added object.",
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
    public ResponseEntity<Maker> add(@Valid @RequestBody MakerDTO dto) {
        return ResponseEntity.ok(service.add(dto));
    }

    @Operation(
            security = @SecurityRequirement(name = "bearerAuth"),
            summary = "This endpoint for authorized users.",
            description = "This endpoint for authorized users. The method update a maker and save changes to database and return updated object.",
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
    public ResponseEntity<Maker> update(@PathVariable long id, @Valid @RequestBody MakerDTO dto) {
        dto.setId(id);
        return ResponseEntity.ok(service.update(dto));
    }

    @Operation(
            security = @SecurityRequirement(name = "bearerAuth"),
            summary = "This endpoint for authorized users.",
            description = "This endpoint for authorized users. The method delete a maker from database.",
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
    public ResponseEntity<String> delete(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.ok("Deleted Successfully");
    }
}
