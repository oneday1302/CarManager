package ua.foxminded.javaspring.carmanager.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

    @GetMapping
    public ResponseEntity<List<BodyType>> getAll(@Valid PaginateAndSort paginateAndSort) {
        return ResponseEntity.ok(service.getAll(paginateAndSort));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BodyType> getById(@PathVariable long id) {
        return ResponseEntity.ok(service.get(id));
    }

    @PostMapping
    public ResponseEntity<BodyType> add(@Valid @RequestBody BodyTypeDTO dto) {
        return ResponseEntity.ok(service.add(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BodyType> update(@PathVariable long id, @Valid @RequestBody BodyTypeDTO dto) {
        dto.setId(id);
        return ResponseEntity.ok(service.update(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.ok("Deleted Successfully");
    }
}
