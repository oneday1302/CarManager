package ua.foxminded.javaspring.carmanager.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.foxminded.javaspring.carmanager.dto.CarDTO;
import ua.foxminded.javaspring.carmanager.dto.PaginateAndSort;
import ua.foxminded.javaspring.carmanager.entity.Car;
import ua.foxminded.javaspring.carmanager.mapper.CarMapper;
import ua.foxminded.javaspring.carmanager.repository.CarRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository repository;
    private final CarMapper mapper;

    @Override
    public Car add(CarDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Param cannot be null.");
        }
        return repository.save(mapper.dtoToCar(dto));
    }

    @Override
    public void addAll(Iterable<Car> cars) {
        if (cars == null) {
            throw new IllegalArgumentException("Param cannot be null.");
        }
        repository.saveAll(cars);
    }

    @Override
    public Car update(CarDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Param cannot be null.");
        }
        Car car = get(dto.getId());
        car.setModel(dto.getModel());
        car.setProductionYear(dto.getProductionYear());
        car.setBodyTypes(dto.getBodyTypes());
        return repository.save(car);
    }

    @Override
    public List<Car> getAll(PaginateAndSort paginateAndSort) {
        if (paginateAndSort == null) {
            throw new IllegalArgumentException("Params cannot be null.");
        }
        return repository.findAll(paginateAndSort.toPageRequest()).getContent();
    }

    @Override
    public Car get(String id) {
        if (id == null) {
            throw new IllegalArgumentException("Param cannot be null.");
        }
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Car not found!"));
    }

    @Override
    public void delete(String id) {
        if (id == null) {
            throw new IllegalArgumentException("Param cannot be null.");
        }
        repository.deleteById(id);
    }

    @Override
    public boolean isEmpty() {
        return repository.count() == 0;
    }
}
