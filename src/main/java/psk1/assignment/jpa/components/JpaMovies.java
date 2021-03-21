package psk1.assignment.jpa.components;

import psk1.assignment.jpa.dao.MoviesDAO;
import psk1.assignment.jpa.entities.*;

import lombok.*;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.*;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class JpaMovies {

    @Inject
    private MoviesDAO moviesDAO;

    @Getter @Setter
    private Movie movieToAdd = new Movie();

    @Getter @Setter
    private Integer year;

    @Getter @Setter
    private Integer month;

    @Getter @Setter
    private Integer day;

    @Getter
    private List<Movie> movies;

    @PostConstruct
    public void init(){
        getAllMovies();
    }

    @Transactional
    public String addMovie(){
        movieToAdd.setReleaseDate(LocalDate.of(year, month, day));
        moviesDAO.create(movieToAdd);
        return "jpa/movies?faces-redirect=true";
    }

    private void getAllMovies(){
        this.movies = moviesDAO.getAll();
    }

    public List<Role> getRolesInMovie(Integer movieId){
        return this.movies.get(movieId-1).getRoles();
    }
}
