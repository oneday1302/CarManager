package ua.foxminded.javaspring.carmanager.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "body_type", schema = "car")
@NoArgsConstructor
@Data
public class BodyType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "body_type_seq")
    @SequenceGenerator(name = "body_type_seq", schema = "car", sequenceName = "body_type_seq", allocationSize = 1)
    private long id;

    @Column(unique = true)
    private String name;

    public BodyType(String name) {
        this.name = name;
    }
}
