package ua.foxminded.javaspring.carmanager.csvParser;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashSet;
import java.util.Set;

@Component
public class ParserImpl implements Parser<CsvRepresentation> {

    @Value("${csv-file-name}")
    private String csvFileName;

    @Override
    public Set<CsvRepresentation> parse() {
        try (InputStream inputStream = new ClassPathResource(csvFileName).getInputStream();
             Reader reader = new InputStreamReader(inputStream);
             CSVParser parser = CSVFormat.EXCEL.builder().setHeader().build().parse(reader)) {

            Set<CsvRepresentation> representations = new HashSet<>();
            for (CSVRecord record : parser) {
                CsvRepresentation representation = CsvRepresentation.builder()
                                                                    .id(record.get("objectId"))
                                                                    .maker(record.get("Make"))
                                                                    .productionYear(record.get("Year"))
                                                                    .model(record.get("Model"))
                                                                    .bodyTypes(record.get("Category"))
                                                                    .build();
                representations.add(representation);
            }
            return representations;
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
}
