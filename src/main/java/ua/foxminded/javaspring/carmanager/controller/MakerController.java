package ua.foxminded.javaspring.carmanager.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

    @GetMapping
    public ResponseEntity<List<Maker>> getAll(@Valid PaginateAndSort paginateAndSort) {
        return ResponseEntity.ok(service.getAll(paginateAndSort));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Maker> getById(@PathVariable long id) {
        return ResponseEntity.ok(service.get(id));
    }

    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping
    public ResponseEntity<Maker> add(@Valid @RequestBody MakerDTO dto) {
        return ResponseEntity.ok(service.add(dto));
    }

    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @PutMapping("/{id}")
    public ResponseEntity<Maker> update(@PathVariable long id, @Valid @RequestBody MakerDTO dto) {
        dto.setId(id);
        return ResponseEntity.ok(service.update(dto));
    }

    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.ok("Deleted Successfully");
    }
}
