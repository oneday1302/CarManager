package ua.foxminded.javaspring.carmanager.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "maker", schema = "car")
@Getter
@EqualsAndHashCode
@ToString
public class Maker {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "maker_seq")
    @SequenceGenerator(name = "maker_seq", schema = "car", sequenceName = "maker_seq", allocationSize = 1)
    private long id;

    @Column(unique = true)
    @Setter
    private String name;

    public Maker(String name) {
        this.name = name;
    }
}
