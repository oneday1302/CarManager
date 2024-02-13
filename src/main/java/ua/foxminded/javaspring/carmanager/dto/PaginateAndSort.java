package ua.foxminded.javaspring.carmanager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class PaginateAndSort {

    @PositiveOrZero
    int page = 0;

    @Positive
    int size = 10;

    @NotBlank
    String sortedByFiled = "id";
}
