package psk1.assignment.jpa.components;

import psk1.assignment.jpa.services.DaysGenerator;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SessionScoped
@Named
public class JpaMovieGoal implements Serializable {

    @Inject
    DaysGenerator DaysGenerator;

    private CompletableFuture<Integer> daysGenerationTask = null;

    public String makeGoal() {
        daysGenerationTask = CompletableFuture.supplyAsync(() -> DaysGenerator.makeGoal());
        return "movies?faces-redirect=true";
    }

    public boolean isMakingGoal() {
        return daysGenerationTask != null && !daysGenerationTask.isDone();
    }

    public String getDaysGeneratorStatus() throws ExecutionException, InterruptedException {
        if (daysGenerationTask == null) {
            return null;
        } else if (isMakingGoal()) {
            return "Generator is making goal. Please wait...";
        }
        return "You have to watch 5 movies in " + daysGenerationTask.get() + " days.";
    }
}
