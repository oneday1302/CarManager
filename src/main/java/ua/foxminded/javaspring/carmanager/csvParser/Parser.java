package ua.foxminded.javaspring.carmanager.csvParser;

import java.util.Set;

public interface Parser<T> {

    Set<T> parse();
}
