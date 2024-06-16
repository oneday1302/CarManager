package ua.foxminded.javaspring.carmanager.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "model", schema = "car")
@NoArgsConstructor
@Data
public class Model {

    @Id
    @SequenceGenerator(name = "model_seq", schema = "car", sequenceName = "model_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "model_seq")
    private long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "maker_id")
    private Maker maker;

    @Column
    private String name;

    public Model(Maker maker, String name) {
        this.maker = maker;
        this.name = name;
    }
}
