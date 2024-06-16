package ua.foxminded.javaspring.carmanager.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

    @GetMapping
    public ResponseEntity<List<Car>> getAll(@Valid PaginateAndSort paginateAndSort) {
        return ResponseEntity.ok(service.getAll(paginateAndSort));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getById(@PathVariable String id) {
        return ResponseEntity.ok(service.get(id));
    }

    @PostMapping
    public ResponseEntity<Car> add(@Valid @RequestBody CarDTO dto) {
        return ResponseEntity.ok(service.add(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> update(@PathVariable String id, @Valid @RequestBody CarDTO dto) {
        dto.setId(id);
        return ResponseEntity.ok(service.update(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.ok("Deleted Successfully");
    }
}
