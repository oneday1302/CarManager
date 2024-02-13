package ua.foxminded.javaspring.carmanager.csvParser;

import ua.foxminded.javaspring.carmanager.fileReader.DataSource;

import java.util.List;

public interface CsvParser {

    List<CsvRepresentation> parseCsvFile(DataSource<String> dataSource);
}
