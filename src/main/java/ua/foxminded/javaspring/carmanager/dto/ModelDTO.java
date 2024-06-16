package ua.foxminded.javaspring.carmanager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ua.foxminded.javaspring.carmanager.entity.Maker;
import ua.foxminded.javaspring.carmanager.validation.annotation.Zero;

@Data
public class ModelDTO {

    @Zero(message = "Id must be zero.")
    private long id;

    @NotNull(message = "Maker must not be null.")
    private Maker maker;

    @NotBlank(message = "Name must not be blank.")
    private String name;
}
