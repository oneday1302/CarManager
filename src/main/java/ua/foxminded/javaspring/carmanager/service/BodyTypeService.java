package ua.foxminded.javaspring.carmanager.service;

import ua.foxminded.javaspring.carmanager.dto.BodyTypeDTO;
import ua.foxminded.javaspring.carmanager.dto.PaginateAndSort;
import ua.foxminded.javaspring.carmanager.entity.BodyType;

import java.util.List;

public interface BodyTypeService {

    BodyType add(BodyTypeDTO dto);

    void addAll(Iterable<BodyType> bodyTypes);

    BodyType update(BodyTypeDTO dto);

    List<BodyType> getAll(PaginateAndSort paginateAndSort);

    BodyType get(long id);

    void delete(long id);

    boolean isEmpty();
}
