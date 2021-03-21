package psk1.assignment.jpa.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "findAllMovies", query = "select m from Movie as m")
})
@Getter @Setter
public class Movie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 100)
    private String title;

    @Column(name = "RELEASE_DATE")
    private LocalDate releaseDate;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Role> roles = new ArrayList<>();

    @ManyToMany
    private List<Producer> producers = new ArrayList<>();
}
