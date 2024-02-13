package ua.foxminded.javaspring.carmanager.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import ua.foxminded.javaspring.carmanager.validation.CreateEntity;
import ua.foxminded.javaspring.carmanager.validation.UpdateEntity;
import ua.foxminded.javaspring.carmanager.validation.annotation.Zero;

@Data
public class MakerDTO {

    @Zero(groups = {CreateEntity.class, UpdateEntity.class}, message = "Id must be zero.")
    private long id;

    @NotBlank(groups = {CreateEntity.class, UpdateEntity.class}, message = "Name must not be blank.")
    private String name;
}
