package ua.foxminded.javaspring.carmanager.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "model", schema = "car")
@Getter
@EqualsAndHashCode
@ToString
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "model_seq")
    @SequenceGenerator(name = "model_seq", schema = "car", sequenceName = "model_seq", allocationSize = 1)
    private long id;

    @ManyToOne
    @JoinColumn(name = "maker_id")
    @Setter
    private Maker maker;

    @Column(unique = true)
    @Setter
    private String name;

    public Model(Maker maker, String name) {
        this.maker = maker;
        this.name = name;
    }
}
