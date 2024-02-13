package ua.foxminded.javaspring.carmanager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ua.foxminded.javaspring.carmanager.entity.Maker;
import ua.foxminded.javaspring.carmanager.validation.CreateEntity;
import ua.foxminded.javaspring.carmanager.validation.UpdateEntity;
import ua.foxminded.javaspring.carmanager.validation.annotation.Zero;

@Data
public class ModelDTO {

    @Zero(groups = {CreateEntity.class, UpdateEntity.class}, message = "Id must be zero.")
    private long id;

    @NotNull(groups = {CreateEntity.class, UpdateEntity.class}, message = "Maker must not be null.")
    private Maker maker;

    @NotBlank(groups = {CreateEntity.class, UpdateEntity.class}, message = "Name must not be blank.")
    private String name;
}
