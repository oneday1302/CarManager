package ua.foxminded.javaspring.carmanager.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.foxminded.javaspring.carmanager.dto.MakerDTO;
import ua.foxminded.javaspring.carmanager.dto.PaginateAndSort;
import ua.foxminded.javaspring.carmanager.entity.Maker;
import ua.foxminded.javaspring.carmanager.service.MakerService;
import ua.foxminded.javaspring.carmanager.validation.CreateEntity;
import ua.foxminded.javaspring.carmanager.validation.UpdateEntity;

import java.util.List;

@RestController
@RequestMapping("/api/v1/makers")
@RequiredArgsConstructor
public class MakerController {

    private final MakerService service;

    @GetMapping
    public ResponseEntity<List<Maker>> getAll(@Valid @ModelAttribute PaginateAndSort paginateAndSort) {
        return ResponseEntity.ok(service.getAll(paginateAndSort));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Maker> getById(@PathVariable long id) {
        return ResponseEntity.ok(service.get(id));
    }

    @PostMapping
    public ResponseEntity<Maker> add(@Validated(CreateEntity.class) @RequestBody MakerDTO dto) {
        return ResponseEntity.ok(service.add(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Maker> update(@PathVariable long id, @Validated(UpdateEntity.class) @RequestBody MakerDTO dto) {
        dto.setId(id);
        return ResponseEntity.ok(service.update(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.ok("Deleted Successfully");
    }
}
