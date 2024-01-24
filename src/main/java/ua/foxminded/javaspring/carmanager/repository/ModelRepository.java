package ua.foxminded.javaspring.carmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.foxminded.javaspring.carmanager.entity.Model;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {

}
