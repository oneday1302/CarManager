package ua.foxminded.javaspring.carmanager;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ua.foxminded.javaspring.carmanager.csvParser.CsvParser;
import ua.foxminded.javaspring.carmanager.entity.BodyType;
import ua.foxminded.javaspring.carmanager.entity.Car;
import ua.foxminded.javaspring.carmanager.entity.Maker;
import ua.foxminded.javaspring.carmanager.entity.Model;
import ua.foxminded.javaspring.carmanager.service.*;
import ua.foxminded.javaspring.carmanager.csvParser.CsvRepresentation;
import ua.foxminded.javaspring.carmanager.fileReader.FileReader;

import java.time.Year;
import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ApplicationStartupRunner implements CommandLineRunner {

    private final BodyTypeService bodyTypeService;
    private final CarService carService;
    private final CsvParser parser;
    private final MakerService makerService;
    private final ModelService modelService;

    @Override
    public void run(String... args) {
        if (bodyTypeService.isEmpty() && carService.isEmpty() && makerService.isEmpty() && modelService.isEmpty()) {
            Set<BodyType> bodyTypes = new HashSet<>();
            Set<Car> cars = new HashSet<>();
            Set<Maker> makers = new HashSet<>();
            Set<Model> models = new HashSet<>();

            for (CsvRepresentation representation : parser.parseCsvFile(new FileReader("file.csv"))) {
                addMakerToList(representation, makers);
                addModelToList(representation, models, makers);
                addBodyTypeToList(representation, bodyTypes);
                addCarToList(representation, cars, models, bodyTypes);
            }

            makerService.addAll(makers);
            modelService.addAll(models);
            bodyTypeService.addAll(bodyTypes);
            carService.addAll(cars);
        }
    }

    private void addMakerToList (CsvRepresentation representation, Set<Maker> makers) {
        makers.add(new Maker(representation.getMaker()));
    }

    private void addModelToList (CsvRepresentation representation, Set<Model> models, Set<Maker> makers) {
        models.add(new Model(makers.stream()
                                   .filter(el -> el.getName().equals(representation.getMaker()))
                                   .findAny()
                                   .orElseThrow(),
                             representation.getModel()));
    }

    private void addBodyTypeToList(CsvRepresentation representation, Set<BodyType> bodyTypes) {
        for (String bodyType : representation.getBodyTypes()) {
            bodyTypes.add(new BodyType(bodyType));
        }
    }

    private void addCarToList(CsvRepresentation representation, Set<Car> cars, Set<Model> models, Set<BodyType> bodyTypes) {
        Car car = Car.builder()
                     .id(representation.getId())
                     .model(models.stream().filter(el -> el.getName().equals(representation.getModel())).findAny().orElseThrow())
                     .productionYear(Year.of(Integer.parseInt(representation.getProductionYear())))
                     .build();
        for (String bodyType : representation.getBodyTypes()) {
            car.addBodyType(bodyTypes.stream().filter(el -> el.getName().equals(bodyType)).findAny().orElseThrow());
        }
        cars.add(car);
    }
}
