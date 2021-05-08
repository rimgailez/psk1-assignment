package psk1.assignment.jpa.components;

import lombok.*;
import psk1.assignment.interceptors.LoggedInvocation;
import psk1.assignment.jpa.dao.*;
import psk1.assignment.jpa.entities.*;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Model
public class JpaRolesInMovie implements Serializable {

    @Inject
    private MoviesDAO moviesDAO;

    @Inject
    private RolesDAO rolesDAO;

    @Inject
    private ProducersDAO producersDAO;

    @Getter @Setter
    private Movie movie;

    @Getter @Setter
    private List<Producer> producers;

    @Getter @Setter
    private Role roleToAdd = new Role();

    @Getter @Setter
    private Integer producer_id = 0;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer movieId = Integer.parseInt(requestParameters.get("movieId"));
        this.movie = moviesDAO.findOne(movieId);
        this.producers = producersDAO.getAll();
    }

    @Transactional
    @LoggedInvocation
    public String addRole() {
        roleToAdd.setMovie(this.movie);
        rolesDAO.create(roleToAdd);
        return "roles?faces-redirect=true&movieId=" + this.movie.getId();
    }

    @Transactional
    @LoggedInvocation
    public String addProducer() {
        List<Producer> producers = movie.getProducers();
        Producer producerToAdd = producersDAO.findOne(producer_id);
        if(!producers.contains(producerToAdd)){
            producers.add(producerToAdd);
            movie.setProducers(producers);
            moviesDAO.update(movie);
        }
        return "roles?faces-redirect=true&movieId=" + this.movie.getId();
    }
}
