package ua.foxminded.javaspring.carmanager.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "body_type", schema = "car")
@NoArgsConstructor
@Data
public class BodyType {

    @Id
    @SequenceGenerator(name = "body_type_seq", schema = "car", sequenceName = "body_type_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "body_type_seq")
    private long id;

    @Column(unique = true)
    private String name;

    public BodyType(String name) {
        this.name = name;
    }
}
