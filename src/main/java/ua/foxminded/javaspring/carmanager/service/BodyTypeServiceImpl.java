package ua.foxminded.javaspring.carmanager.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.foxminded.javaspring.carmanager.dto.BodyTypeDTO;
import ua.foxminded.javaspring.carmanager.dto.PaginateAndSort;
import ua.foxminded.javaspring.carmanager.entity.BodyType;
import ua.foxminded.javaspring.carmanager.repository.BodyTypeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BodyTypeServiceImpl implements BodyTypeService {

    private final BodyTypeRepository repository;

    @Override
    public BodyType add(BodyTypeDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Param cannot be null.");
        }
        return repository.save(new BodyType(dto.getName()));
    }

    @Override
    public BodyType update(BodyTypeDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Param cannot be null.");
        }
        BodyType bodyType = get(dto.getId());
        bodyType.setName(dto.getName());
        return repository.save(bodyType);
    }

    @Override
    public List<BodyType> getAll(PaginateAndSort paginateAndSort) {
        if (paginateAndSort == null) {
            throw new IllegalArgumentException("Params cannot be null.");
        }
        return repository.findAll(paginateAndSort.toPageRequest()).getContent();
    }

    @Override
    public BodyType get(long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Body type not found!"));
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean isEmpty() {
        return repository.count() == 0;
    }
}
