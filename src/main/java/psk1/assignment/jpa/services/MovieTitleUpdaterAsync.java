package psk1.assignment.jpa.services;

import psk1.assignment.jpa.entities.Movie;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@ApplicationScoped
public class MovieTitleUpdaterAsync {

    @PersistenceContext
    @Async
    private EntityManager entityManager;

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public String update(Integer id, String title, long wait1, long wait2) {
        try {
            Thread.sleep(wait1);
            Movie movie = entityManager.find(Movie.class, id, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
            System.out.println("Updating with " + title + ", version: " + movie.getVersion());
            Thread.sleep(wait2);
            movie.setTitle(title);
            System.out.println("Flushing with " + movie.getTitle() + ", version: " + movie.getVersion());
            entityManager.flush();
        }
        catch (OptimisticLockException e) {
            System.out.println("OPTIMISTIC LOCK EXCEPTION IN MovieUpdaterAsync");
            return "Optimistic Lock Exception";
        }
        catch (InterruptedException e) {}
        return "Update finished";
    }
}