package ua.foxminded.javaspring.carmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.foxminded.javaspring.carmanager.entity.BodyType;

@Repository
public interface BodyTypeRepository extends JpaRepository<BodyType, Long> {

}
