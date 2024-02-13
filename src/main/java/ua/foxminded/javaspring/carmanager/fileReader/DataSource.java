package ua.foxminded.javaspring.carmanager.fileReader;

import java.io.IOException;
import java.util.stream.Stream;

public interface DataSource<T> {

    Stream<T> getData();
}
