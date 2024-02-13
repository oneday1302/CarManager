package ua.foxminded.javaspring.carmanager.csvParser;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode
@ToString
@Getter
public class CsvRepresentation {

    private final String id;
    private final String maker;
    private final String productionYear;
    private final String model;
    private final Set<String> bodyTypes = new HashSet<>();

    @Builder
    public CsvRepresentation(String id, String maker, String productionYear, String model) {
        this.id = id;
        this.maker = maker;
        this.productionYear = productionYear;
        this.model = model;
    }

    public void addBodyType(String bodyType) {
        bodyTypes.add(bodyType);
    }
}
