package ua.foxminded.javaspring.carmanager.fileReader;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class FileReader implements DataSource<String> {

    private final String fileName;

    @Override
    public Stream<String> getData() {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new ClassPathResource(fileName).getInputStream()))) {
            List<String> list = new LinkedList<>();
            while (reader.ready()) {
                list.add(reader.readLine());
            }
            return list.stream();
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }
}
