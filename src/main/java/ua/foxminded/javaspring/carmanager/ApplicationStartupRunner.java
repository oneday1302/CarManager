package ua.foxminded.javaspring.carmanager;

import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import ua.foxminded.javaspring.carmanager.entity.BodyType;
import ua.foxminded.javaspring.carmanager.entity.Car;
import ua.foxminded.javaspring.carmanager.entity.Maker;
import ua.foxminded.javaspring.carmanager.entity.Model;
import ua.foxminded.javaspring.carmanager.service.*;

import java.io.*;
import java.time.Year;
import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ApplicationStartupRunner implements CommandLineRunner {

    private final BodyTypeService bodyTypeService;
    private final CarService carService;
    private final MakerService makerService;
    private final ModelService modelService;

    @Value("${csv-file-name}")
    private String csvFileName;

    @Override
    public void run(String... args) throws IOException {
        if (bodyTypeService.isEmpty() && carService.isEmpty() && makerService.isEmpty() && modelService.isEmpty()) {
            try(InputStream inputStream = new ClassPathResource(csvFileName).getInputStream();
                Reader reader = new InputStreamReader(inputStream);
                CSVParser parser = CSVFormat.EXCEL.builder().setHeader().build().parse(reader)) {

                Set<BodyType> bodyTypes = new HashSet<>();
                Set<Car> cars = new HashSet<>();
                Set<Maker> makers = new HashSet<>();
                Set<Model> models = new HashSet<>();
                for (CSVRecord record : parser) {
                    CsvRepresentation representation = CsvRepresentation.builder()
                                                                        .id(record.get("objectId"))
                                                                        .maker(record.get("Make"))
                                                                        .productionYear(record.get("Year"))
                                                                        .model(record.get("Model"))
                                                                        .bodyTypes(record.get("Category"))
                                                                        .build();
                    addMakerToList(representation, makers);
                    addModelToList(representation, models, makers);
                    addBodyTypeToList(representation, bodyTypes);
                    addCarToList(representation, cars, models, bodyTypes);
                }
                carService.addAll(cars);
            }
        }
    }

    private void addMakerToList (CsvRepresentation representation, Set<Maker> makers) {
        makers.add(new Maker(representation.maker()));
    }

    private void addModelToList (CsvRepresentation representation, Set<Model> models, Set<Maker> makers) {
        models.add(new Model(makers.stream()
                                   .filter(el -> el.getName().equals(representation.maker()))
                                   .findAny()
                                   .orElseThrow(),
                             representation.model()));
    }

    private void addBodyTypeToList(CsvRepresentation representation, Set<BodyType> bodyTypes) {
        for (String bodyType : representation.bodyTypes().replace(" ", "").split(",")) {
            bodyTypes.add(new BodyType(bodyType));
        }
    }

    private void addCarToList(CsvRepresentation representation, Set<Car> cars, Set<Model> models, Set<BodyType> bodyTypes) {
        Car car = Car.builder()
                     .id(representation.id())
                     .model(models.stream().filter(el -> el.getName().equals(representation.model())).findAny().orElseThrow())
                     .productionYear(Year.of(Integer.parseInt(representation.productionYear())))
                     .build();
        for (String bodyType : representation.bodyTypes().replace(" ", "").split(",")) {
            car.addBodyType(bodyTypes.stream().filter(el -> el.getName().equals(bodyType)).findAny().orElseThrow());
        }
        cars.add(car);
    }
}
