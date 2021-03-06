package psk1.assignment.jpa.dao;

import psk1.assignment.jpa.entities.Movie;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class MoviesDAO {

    @Inject @Default
    private EntityManager entityManager;

    public void setEntityManager(EntityManager em) {
        this.entityManager = em;
    }

    public List<Movie> getAll() {
        return entityManager.createNamedQuery("findAllMovies", Movie.class).getResultList();
    }

    public void create(Movie movie){
        this.entityManager.persist(movie);
    }

    public Movie update(Movie movie){
        return entityManager.merge(movie);
    }

    public Movie findOne(Integer id){
        return entityManager.find(Movie.class, id);
    }
}
