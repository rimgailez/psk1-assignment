package psk1.assignment.jpa.components;

import psk1.assignment.interceptors.LoggedInvocation;
import psk1.assignment.jpa.services.MovieTitleUpdaterAsync;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SessionScoped
@Named
public class JpaUpdateMovieTitle implements Serializable{

    private Integer movieId = 1;

    @Inject
    private MovieTitleUpdaterAsync updater;

    private CompletableFuture update1Future = null;

    private CompletableFuture update2Future = null;

    @LoggedInvocation
    public String test(){
        update1Future = CompletableFuture.supplyAsync(() -> updater.update(movieId,"Christmas1", 0, 1000));
        update2Future = CompletableFuture.supplyAsync(() -> updater.update(movieId,"Christmas2", 200, 100));
        return "updateTitle?faces-redirect=true";
    }

    public boolean isUpdate1Running() {
        return this.update1Future != null && !this.update1Future.isDone();
    }
    public boolean isUpdate2Running() {
        return this.update2Future != null && !this.update2Future.isDone();
    }

    public String getUpdate1Status() throws ExecutionException, InterruptedException {
        if (update1Future == null) {
            return null;
        } else if (isUpdate1Running()) {
            return "Update 1 in progress...";
        }
        return "Update1 Status: " + update1Future.get();
    }
    public String getUpdate2Status() throws ExecutionException, InterruptedException {
        if (update2Future == null) {
            return null;
        } else if (isUpdate2Running()) {
            return "Update 2 in progress...";
        }
        return "Update2 Status: " + update2Future.get();
    }
}
