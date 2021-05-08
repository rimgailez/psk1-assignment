package psk1.assignment.jpa.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "findAllProducers", query = "select p from Producer as p")
})
@Getter @Setter
public class Producer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 25)
    @Column(name = "FIRST_NAME")
    private String firstName;

    @Size(max = 25)
    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "DATE_OF_BIRTH")
    private LocalDate dateOfBirth;

    @ManyToMany(mappedBy = "producers")
    @Column(name = "PRODUCED_MOVIES")
    private List<Movie> movies = new ArrayList<>();

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer version;
}
