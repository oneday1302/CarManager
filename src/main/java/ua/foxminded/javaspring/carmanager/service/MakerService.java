package ua.foxminded.javaspring.carmanager.service;

import ua.foxminded.javaspring.carmanager.dto.MakerDTO;
import ua.foxminded.javaspring.carmanager.dto.PaginateAndSort;
import ua.foxminded.javaspring.carmanager.entity.Maker;

import java.util.List;

public interface MakerService {

    Maker add(MakerDTO dto);

    void addAll(Iterable<Maker> entities);

    Maker update(MakerDTO dto);

    List<Maker> getAll(PaginateAndSort paginateAndSort);

    Maker get(long id);

    void delete(long id);

    boolean isEmpty();
}
