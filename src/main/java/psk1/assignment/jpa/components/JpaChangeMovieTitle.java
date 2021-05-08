package psk1.assignment.jpa.components;

import psk1.assignment.jpa.dao.MoviesDAO;
import psk1.assignment.jpa.entities.Movie;

import lombok.*;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.*;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@Model
public class JpaChangeMovieTitle implements Serializable {

    @Inject
    private MoviesDAO moviesDAO;

    @Getter @Setter
    private Movie movie;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer movieId = Integer.parseInt(requestParameters.get("movieId"));
        this.movie = moviesDAO.findOne(movieId);
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public String changeMovieTitle() {
        try {
            moviesDAO.update(this.movie);
        } catch (OptimisticLockException ex) {
            return "changeTitle?faces-redirect=true&movieId=" + this.movie.getId() + "&error=optimistic-lock-exception";
        }
        return "movies?faces-redirect=true";
    }
}
