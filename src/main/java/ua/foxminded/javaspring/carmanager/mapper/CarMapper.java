package ua.foxminded.javaspring.carmanager.mapper;

import ua.foxminded.javaspring.carmanager.dto.CarDTO;
import ua.foxminded.javaspring.carmanager.entity.Car;

public interface CarMapper {

    Car dtoToCar(CarDTO dto);
}
