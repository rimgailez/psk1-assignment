package psk1.assignment.jpa.dao;

import psk1.assignment.jpa.entities.Role;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class RolesDAO {

    @Inject
    private EntityManager entityManager;

    public List<Role> getAll() {
        return entityManager.createNamedQuery("findAllRoles", Role.class).getResultList();
    }

    public void create(Role role){
        this.entityManager.persist(role);
    }

    public Role findOne(Integer id){
        return entityManager.find(Role.class, id);
    }

    public Role update(Role role){
        return entityManager.merge(role);
    }

    public List<Role> findByMovie(Integer movieId) {
        return entityManager.createQuery("SELECT r FROM Role r WHERE r.movie.id = :movieId")
                .setParameter("movieId", movieId)
                .getResultList();
    }
}
