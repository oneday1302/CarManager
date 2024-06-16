package ua.foxminded.javaspring.carmanager.service;

import ua.foxminded.javaspring.carmanager.dto.ModelDTO;
import ua.foxminded.javaspring.carmanager.dto.PaginateAndSort;
import ua.foxminded.javaspring.carmanager.entity.Model;

import java.util.List;

public interface ModelService {

    Model add(ModelDTO dto);

    Model update(ModelDTO dto);

    List<Model> getAll(PaginateAndSort paginateAndSort);

    Model get(long id);

    void delete(long id);

    boolean isEmpty();
}
