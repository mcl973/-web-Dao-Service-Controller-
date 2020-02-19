/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: AfterAop
 * Author:   Administrator
 * Date:     2020/02/19 10:10
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */
package MyService.AopMethods.After;

import MyService.AopMethods.BaseInterface;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2020/02/19
 * @since 1.0.0
 */
public class AfterAop implements BaseInterface {

    @Override
    public Object Excute(Method method, Object object, Object[] objects) {
        try {
            Object invoke = method.invoke(object, objects);
            after();
            return invoke;
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        after();
        return null;
    }
    private void after(){
        System.out.println("thisis after test...");
    }
}
