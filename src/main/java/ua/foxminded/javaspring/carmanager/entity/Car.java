package ua.foxminded.javaspring.carmanager.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Year;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "car", schema = "car")
@NoArgsConstructor
@Data
public class Car {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;

    @Column
    private Year productionYear;

    @ManyToMany
    @JoinTable(
            name = "car_body_type",
            schema = "car",
            joinColumns = @JoinColumn(name = "car_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "body_type_id", referencedColumnName = "id"))
    private Set<BodyType> bodyTypes = new HashSet<>();

    @Builder
    public Car(String id, Model model, Year productionYear, Set<BodyType> bodyTypes) {
        this.id = id;
        this.model = model;
        this.productionYear = productionYear;
        this.bodyTypes = bodyTypes;
    }

    public void addBodyType(BodyType bodyType) {
        if (bodyType == null) {
            throw new IllegalArgumentException("Param cannot be null.");
        }
        this.bodyTypes.add(bodyType);
    }
}
