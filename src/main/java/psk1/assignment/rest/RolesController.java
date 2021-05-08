package psk1.assignment.rest;

import lombok.Getter;
import lombok.Setter;
import psk1.assignment.jpa.dao.*;
import psk1.assignment.jpa.entities.*;
import psk1.assignment.rest.contracts.RoleDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/roles")
public class RolesController {

    @Inject
    @Getter @Setter
    private MoviesDAO moviesDAO;

    @Inject
    @Getter @Setter
    private RolesDAO rolesDAO;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id) {
        Role role = rolesDAO.findOne(id);
        if (role == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setName(role.getName());
        roleDTO.setActor(role.getActor());
        roleDTO.setDescription(role.getDescription());
        roleDTO.setMovieId(role.getMovie().getId());

        return Response.ok(roleDTO).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(RoleDTO roleDTO) {
        Role role = new Role();
        Movie movie = moviesDAO.findOne(roleDTO.getMovieId());
        if (movie == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        role.setName(roleDTO.getName());
        role.setActor(roleDTO.getActor());
        role.setDescription(roleDTO.getDescription());
        role.setMovie(movie);
        rolesDAO.create(role);

        return Response.ok().build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public Response update(@PathParam("id") final Integer id, RoleDTO roleDTO) throws InterruptedException {
        try {
            Role role = rolesDAO.findOne(id);
            Movie movie = moviesDAO.findOne(roleDTO.getMovieId());
            if (role == null || movie == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            Thread.sleep(5000);
            role.setName(roleDTO.getName());
            role.setActor(roleDTO.getActor());
            role.setDescription(roleDTO.getDescription());
            role.setMovie(movie);
            rolesDAO.update(role);
            return Response.ok().build();
        } catch (OptimisticLockException ex) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}
