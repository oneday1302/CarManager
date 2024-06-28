package ua.foxminded.javaspring.carmanager.csvParser;

import lombok.*;

public record CsvRepresentation(String id, String maker, String productionYear, String model, String bodyTypes) {

    @Builder
    public CsvRepresentation {
    }
}
