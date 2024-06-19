package ua.foxminded.javaspring.carmanager.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "maker", schema = "car")
@NoArgsConstructor
@Data
public class Maker {

    @Id
    @SequenceGenerator(name = "maker_seq", schema = "car", sequenceName = "maker_seq", allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "maker_seq")
    private long id;

    @Column(unique = true)
    private String name;

    public Maker(String name) {
        this.name = name;
    }
}
