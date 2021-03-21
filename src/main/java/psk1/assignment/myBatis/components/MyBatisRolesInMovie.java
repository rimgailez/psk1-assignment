package psk1.assignment.myBatis.components;

import lombok.*;
import psk1.assignment.jpa.dao.*;
import psk1.assignment.jpa.entities.*;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@Model
public class MyBatisRolesInMovie implements Serializable {

    @Inject
    private MoviesDAO moviesDAO;

    @Inject
    private RolesDAO rolesDAO;

    @Getter @Setter
    private Movie movie;

    @Getter @Setter
    private Role roleToAdd = new Role();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer movieId = Integer.parseInt(requestParameters.get("movieId"));
        this.movie = moviesDAO.findOne(movieId);
    }

    @Transactional
    public String addRole() {
        roleToAdd.setMovie(this.movie);
        rolesDAO.create(roleToAdd);
        return "myBatis/roles?faces-redirect=true&movieId=" + this.movie.getId();
    }
}
