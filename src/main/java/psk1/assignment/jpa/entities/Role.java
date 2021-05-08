package psk1.assignment.jpa.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@NamedQueries({
        @NamedQuery(name = "findAllRoles", query = "select r from Role as r")
})
@Getter @Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @Column(name = "ROLE_NAME")
    private String name;

    @Size(max = 200)
    private String description;

    @Size(max = 50)
    @Column(name = "ACTOR_NAME")
    private String actor;

    @ManyToOne
    @JoinColumn(name = "MOVIE_ID")
    private Movie movie;

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer version;
}
