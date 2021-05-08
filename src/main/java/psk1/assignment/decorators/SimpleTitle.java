package psk1.assignment.decorators;

import javax.enterprise.context.RequestScoped;
import java.io.Serializable;
import java.util.logging.Logger;

@RequestScoped
public class SimpleTitle implements Title, Serializable {

    private static final Logger log = Logger.getLogger(SimpleTitle.class.getName());

    @Override
    public void logTitle(String title) {
        log.info(title);
    }
}
