package psk1.assignment.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;
import java.lang.reflect.Parameter;

@Interceptor
@LoggedInvocation
public class MethodLogger implements Serializable {
    @AroundInvoke
    public Object log(InvocationContext ctx) throws Exception {
        System.out.println("Method called: " + ctx.getMethod().getName());
        Parameter[] parameters = ctx.getMethod().getParameters();
        if (parameters.length != 0) {
            System.out.println("With parameters: ");
            for (Parameter param : parameters) {
                System.out.println(param.getName());
            }
        }
        return ctx.proceed();
    }
}
