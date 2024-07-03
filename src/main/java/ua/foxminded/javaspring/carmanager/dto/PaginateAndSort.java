package ua.foxminded.javaspring.carmanager.dto;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Data
public class PaginateAndSort {

    @PositiveOrZero
    int page = 0;

    @Positive
    int size = 10;

    String sortedByFiled = "id";

    public PageRequest toPageRequest() {
        return PageRequest.of(page, size, Sort.by(sortedByFiled));
    }
}
