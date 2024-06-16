package ua.foxminded.javaspring.carmanager.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import ua.foxminded.javaspring.carmanager.validation.annotation.Zero;

@Data
public class BodyTypeDTO {

    @Zero(message = "Id must be zero.")
    private long id;

    @NotBlank(message = "Name must not be blank.")
    private String name;
}
