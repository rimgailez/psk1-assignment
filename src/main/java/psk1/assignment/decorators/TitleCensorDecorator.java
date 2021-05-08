package psk.practice.decorators;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import java.util.logging.Logger;

@Decorator
public abstract class TitleCensorDecorator implements Title {
    private static final Logger log = Logger.getLogger(TitleCensorDecorator.class.getName());

    @Inject @Delegate @Any
    Title title;

    @Override
    public void logTitle(String title) {
        this.title.logTitle(title);
        if (title.startsWith("BadWord")) {
            log.warning("Title has some censored content: " + title);
        }
    }
}
