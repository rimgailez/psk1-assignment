package psk1.assignment.jpa.components;

import psk1.assignment.decorators.SimpleTitle;
import psk1.assignment.interceptors.LoggedInvocation;
import psk1.assignment.jpa.dao.*;
import psk1.assignment.jpa.entities.*;

import lombok.*;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.*;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Model
public class JpaMovies {

    @Inject
    private MoviesDAO moviesDAO;

    @Inject
    private SimpleTitle title;

    @Inject
    private RolesDAO rolesDAO;

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
    @LoggedInvocation
    public String addMovie(){
        title.logTitle(movieToAdd.getTitle());
        movieToAdd.setReleaseDate(LocalDate.of(year, month, day));
        moviesDAO.create(movieToAdd);
        return "movies?faces-redirect=true";
    }

    private void getAllMovies(){
        this.movies = moviesDAO.getAll();
    }

    public List<Role> getRolesInMovie(Integer movieId){
        return rolesDAO.findByMovie(movieId);
    }
}
