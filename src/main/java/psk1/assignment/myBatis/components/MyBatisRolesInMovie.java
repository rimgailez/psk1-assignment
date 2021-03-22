package psk1.assignment.myBatis.components;

import lombok.*;
import psk1.assignment.myBatis.dao.*;
import psk1.assignment.myBatis.model.Movie;
import psk1.assignment.myBatis.model.Role;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Map;

@Model
public class MyBatisRolesInMovie {

    @Inject
    private MovieMapper movieMapper;

    @Inject
    private RoleMapper roleMapper;

    @Getter @Setter
    private Movie movie;

    @Getter @Setter
    private Role roleToAdd = new Role();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer movieId = Integer.parseInt(requestParameters.get("movieId"));
        this.movie = movieMapper.selectByPrimaryKey(movieId);
        this.movie.setRoles(roleMapper.selectRolesInMovie(movieId));
    }

    @Transactional
    public String addRole() {
        roleToAdd.setMovieId(this.movie.getId());
        roleMapper.insert(roleToAdd);
        return "roles?faces-redirect=true&movieId=" + this.movie.getId();
    }
}
