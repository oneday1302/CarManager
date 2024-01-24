package ua.foxminded.javaspring.carmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.foxminded.javaspring.carmanager.entity.Maker;

@Repository
public interface MakerRepository extends JpaRepository<Maker, Long> {

}
