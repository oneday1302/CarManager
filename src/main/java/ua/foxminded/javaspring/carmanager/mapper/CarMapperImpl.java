package ua.foxminded.javaspring.carmanager.mapper;

import org.springframework.stereotype.Component;
import ua.foxminded.javaspring.carmanager.dto.CarDTO;
import ua.foxminded.javaspring.carmanager.entity.Car;

@Component
public class CarMapperImpl implements CarMapper {

    @Override
    public Car dtoToCar(CarDTO dto) {
        Car car = Car.builder()
                     .id(dto.getId())
                     .model(dto.getModel())
                     .productionYear(dto.getProductionYear())
                     .build();
        car.addAllBodyType(dto.getBodyTypes());
        return car;
    }
}
