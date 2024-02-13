package ua.foxminded.javaspring.carmanager.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ua.foxminded.javaspring.carmanager.dto.MakerDTO;
import ua.foxminded.javaspring.carmanager.dto.PaginateAndSort;
import ua.foxminded.javaspring.carmanager.entity.Maker;
import ua.foxminded.javaspring.carmanager.repository.MakerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MakerServiceImpl implements MakerService {

    private final MakerRepository repository;

    @Override
    public Maker add(MakerDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Param cannot be null.");
        }
        return repository.save(new Maker(dto.getName()));
    }

    @Override
    public void addAll(Iterable<Maker> entities) {
        if (entities == null) {
            throw new IllegalArgumentException("Param cannot be null.");
        }
        repository.saveAll(entities);
    }

    @Override
    public Maker update(MakerDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Param cannot be null.");
        }
        Maker maker = get(dto.getId());
        maker.setName(dto.getName());
        return repository.save(maker);
    }

    @Override
    public List<Maker> getAll(PaginateAndSort paginateAndSort) {
        if (paginateAndSort == null) {
            throw new IllegalArgumentException("Params cannot be null.");
        }
        return repository.findAll(PageRequest.of(paginateAndSort.getPage(),
                                                 paginateAndSort.getSize(),
                                                 Sort.by(paginateAndSort.getSortedByFiled())))
                         .getContent();
    }

    @Override
    public Maker get(long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Maker not found!"));
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
