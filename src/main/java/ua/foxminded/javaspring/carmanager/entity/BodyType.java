package ua.foxminded.javaspring.carmanager.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "body_type", schema = "car")
@Getter
@EqualsAndHashCode
@ToString
public class BodyType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "body_type_seq")
    @SequenceGenerator(name = "body_type_seq", schema = "car", sequenceName = "body_type_seq", allocationSize = 1)
    private long id;

    @Column(unique = true)
    @Setter
    private String name;

    public BodyType(String name) {
        this.name = name;
    }
}
