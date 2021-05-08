package psk1.assignment.jpa.services;

import javax.enterprise.context.ApplicationScoped;
import java.util.Random;

@ApplicationScoped
public class DaysGenerator {

    public Integer makeGoal() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) { }
        Integer goal = new Random().nextInt(30);
        return goal;
    }
}
