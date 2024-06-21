package ua.foxminded.javaspring.carmanager.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ua.foxminded.javaspring.carmanager.entity.BodyType;
import ua.foxminded.javaspring.carmanager.entity.Model;

import java.time.Year;
import java.util.Set;

@Data
public class CarDTO {


    @NotBlank(message = "Id must not be blank.")
    private String id;

    @NotNull(message = "Model must not be null.")
    private Model model;

    @NotNull(message = "Year must not be null.")
    @JsonFormat(pattern = "yyyy", shape = JsonFormat.Shape.OBJECT)
    private Year productionYear;

    @NotNull(message = "BodyTypes must not be null.")
    private Set<BodyType> bodyTypes;
}
