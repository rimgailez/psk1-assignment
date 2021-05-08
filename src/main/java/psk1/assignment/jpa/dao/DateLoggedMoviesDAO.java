package psk1.assignment.jpa.dao;

import psk1.assignment.jpa.entities.Movie;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;
import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
@Specializes
public class DateLoggedMoviesDAO extends MoviesDAO{

    private void LogReleaseDate(Movie movie){
        if(movie.getReleaseDate().isBefore(LocalDate.now())){
            System.out.println("Movie " + movie.getTitle() + " has already been released.");
        }
        else System.out.println("Movie " + movie.getTitle() + " will be released on " + movie.getReleaseDate() + ".");
    }

    public List<Movie> getAll() {
        List<Movie> movies = super.getAll();
        for (Movie movie: movies) {
            LogReleaseDate(movie);
        }
        return movies;
    }

    public void create(Movie movie){
        super.create(movie);
        LogReleaseDate(movie);
    }

    public Movie update(Movie movie){
        Movie updatedMovie = super.update(movie);
        LogReleaseDate(updatedMovie);
        return updatedMovie;
    }

    public Movie findOne(Integer id){
        Movie movie = super.findOne(id);
        LogReleaseDate(movie);
        return movie;
    }
}
