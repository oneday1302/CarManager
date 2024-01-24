package ua.foxminded.javaspring.carmanager.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Year;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "car", schema = "car")
@Getter
@EqualsAndHashCode
@ToString
public class Car {

    @Id
    @Setter
    private String id;

    @ManyToOne
    @JoinColumn(name = "model_id")
    @Setter
    private Model model;

    @Column(name = "production_year")
    @Setter
    private Year productionYear;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "car_body_type",
            schema = "car",
            joinColumns = @JoinColumn(name = "car_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "body_type_id", referencedColumnName = "id"))
    private final Set<BodyType> bodyTypes = new HashSet<>();

    public Car(String id, Model model, Year productionYear) {
        this.id = id;
        this.model = model;
        this.productionYear = productionYear;
    }

    public void addBodyTypes(Set<BodyType> bodyTypes) {
        if (bodyTypes == null) {
            throw new IllegalArgumentException("Param cannot be null.");
        }
        this.bodyTypes.addAll(bodyTypes);
    }
}
