package ua.foxminded.javaspring.carmanager.csvParser;

import org.springframework.stereotype.Service;
import ua.foxminded.javaspring.carmanager.fileReader.DataSource;

import java.util.ArrayList;
import java.util.List;

@Service
public class CsvParserImpl implements CsvParser {

    private static final int MIN_COUNT_OF_COLUMNS = 5;
    private static final int FIRST_COLUMN = 0;
    private static final int SECOND_COLUMN = 1;
    private static final int THIRD_COLUMN = 2;
    private static final int FOURTH_COLUMN = 3;
    private static final int FIFTH_COLUMN = 4;

    @Override
    public List<CsvRepresentation> parseCsvFile(DataSource<String> dataSource) {
        if (dataSource == null) {
            throw new IllegalArgumentException("Param cannot be null.");
        }
        List<String> lines = dataSource.getData().toList();
        List<CsvRepresentation> csvRepresentations = new ArrayList<>();
        for(int i = 1; i < lines.size(); i++) {
            String[] columns = lines.get(i)
                                    .replace("\"", "")
                                    .replace(" ", "")
                                    .split(",");
            if (columns.length < MIN_COUNT_OF_COLUMNS) {
                throw new IllegalArgumentException("Columns must be not less than five.");
            }
            CsvRepresentation csvRepresentation = CsvRepresentation.builder()
                                                                   .id(columns[FIRST_COLUMN])
                                                                   .maker(columns[SECOND_COLUMN])
                                                                   .productionYear(columns[THIRD_COLUMN])
                                                                   .model(columns[FOURTH_COLUMN])
                                                                   .build();
            for (int j = FIFTH_COLUMN; j < columns.length; j++) {
                csvRepresentation.addBodyType(columns[j]);
            }
            csvRepresentations.add(csvRepresentation);
        }
        return csvRepresentations;
    }
}
