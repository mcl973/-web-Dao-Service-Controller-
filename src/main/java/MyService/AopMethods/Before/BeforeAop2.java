/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: BeforeAop2
 * Author:   Administrator
 * Date:     2020/02/25 21:37
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */
package MyService.AopMethods.Before;

import MyService.AopMethods.BaseInterface;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2020/02/25
 * @since 1.0.0
 */
public class BeforeAop2 implements BaseInterface {
    @Override
    public Object Excute(Method method, Object object, Object[] objects) {
        String before = before((String) objects[0]);
        if (before == null)
            return null;
        try {
            return method.invoke(object, objects);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String before(String name){
        if (name.equals(""))
            return null;
        else return name;
    }
}
