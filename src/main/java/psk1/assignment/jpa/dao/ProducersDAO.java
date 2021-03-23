package psk1.assignment.jpa.dao;

import psk1.assignment.jpa.entities.Producer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class ProducersDAO {

    @Inject
    private EntityManager entityManager;

    public void setEntityManager(EntityManager em) {
        this.entityManager = em;
    }

    public List<Producer> getAll() {
        return entityManager.createNamedQuery("findAllProducers", Producer.class).getResultList();
    }

    public void create(Producer producer){
        this.entityManager.persist(producer);
    }

    public Producer update(Producer producer){
        return entityManager.merge(producer);
    }

    public Producer findOne(Integer id){
        return entityManager.find(Producer.class, id);
    }

}
