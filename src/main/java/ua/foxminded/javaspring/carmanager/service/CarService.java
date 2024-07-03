package ua.foxminded.javaspring.carmanager.service;

import ua.foxminded.javaspring.carmanager.dto.CarDTO;
import ua.foxminded.javaspring.carmanager.dto.PaginateAndSort;
import ua.foxminded.javaspring.carmanager.entity.Car;

import java.util.List;

public interface CarService {

    Car add(CarDTO dto);

    void addAll(Iterable<Car> cars);

    Car update(CarDTO dto);

    List<Car> getAll(PaginateAndSort paginateAndSort);

    Car get(String id);

    void delete(String id);

    boolean existsById(String id);

    boolean isEmpty();
}
