package ua.foxminded.javaspring.carmanager.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.foxminded.javaspring.carmanager.dto.ModelDTO;
import ua.foxminded.javaspring.carmanager.dto.PaginateAndSort;
import ua.foxminded.javaspring.carmanager.entity.Model;
import ua.foxminded.javaspring.carmanager.repository.ModelRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModelServiceImpl implements ModelService {

    private final ModelRepository repository;

    @Override
    public Model add(ModelDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Param cannot be null.");
        }
        return repository.save(new Model(dto.getMaker(), dto.getName()));
    }

    @Override
    public void addAll(Iterable<Model> models) {
        if (models == null) {
            throw new IllegalArgumentException("Param cannot be null.");
        }
        repository.saveAll(models);
    }

    @Override
    public Model update(ModelDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Param cannot be null.");
        }
        Model model = get(dto.getId());
        model.setMaker(dto.getMaker());
        model.setName(dto.getName());
        return repository.save(model);
    }

    @Override
    public List<Model> getAll(PaginateAndSort paginateAndSort) {
        if (paginateAndSort == null) {
            throw new IllegalArgumentException("Params cannot be null.");
        }
        return repository.findAll(paginateAndSort.toPageRequest()).getContent();
    }


    @Override
    public Model get(long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Model not found!"));
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
