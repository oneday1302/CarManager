package ua.foxminded.javaspring.carmanager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ua.foxminded.javaspring.carmanager.entity.BodyType;
import ua.foxminded.javaspring.carmanager.entity.Model;
import ua.foxminded.javaspring.carmanager.validation.CreateEntity;
import ua.foxminded.javaspring.carmanager.validation.UpdateEntity;

import java.time.Year;
import java.util.Set;

@Data
public class CarDTO {


    @NotBlank(groups = {CreateEntity.class, UpdateEntity.class}, message = "Id must not be blank.")
    private String id;

    @NotNull(groups = {CreateEntity.class, UpdateEntity.class}, message = "Model must not be null.")
    private Model model;

    @NotNull(groups = {CreateEntity.class, UpdateEntity.class}, message = "Year must not be null.")
    private Year productionYear;

    @NotNull(groups = {CreateEntity.class, UpdateEntity.class}, message = "BodyTypes must not be null.")
    private Set<BodyType> bodyTypes;
}
