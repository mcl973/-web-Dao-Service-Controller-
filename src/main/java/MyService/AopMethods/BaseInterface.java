package MyService.AopMethods;

import java.lang.reflect.Method;

public interface BaseInterface {
    public Object Excute(Method method, Object object, Object[] objects);
}
