package psk1.assignment.alternatives;

import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Model;

@Model
@Alternative
public class InformalGreeting implements Greeting {

    @Override
    public String sayHello(){
        return "Hey!";
    }
}
