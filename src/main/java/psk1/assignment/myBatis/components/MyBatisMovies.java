package psk1.assignment.myBatis.components;

import lombok.Getter;
import lombok.Setter;
import psk1.assignment.myBatis.dao.MovieMapper;
import psk1.assignment.myBatis.model.Movie;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Model
public class MyBatisMovies {
    @Inject
    private MovieMapper movieMapper;

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
    public void init() {
        this.getAllMovies();
    }

    @Transactional
    public String addMovie() {
        movieToAdd.setReleaseDate(Date.valueOf(LocalDate.of(year, month, day)));
        movieMapper.insert(movieToAdd);
        return "/myBatis/movies?faces-redirect=true";
    }

    private void getAllMovies() {
        this.movies = movieMapper.selectAll();
    }
}
