/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: JAutoAop
 * Author:   Administrator
 * Date:     2020/02/19 10:53
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */
package AutoJdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2020/02/19
 * @since 1.0.0
 */
public class JAutoAop {
    public static  Object createNewInstance(Object object, InvocationHandler handler){
        return Proxy.newProxyInstance(object.getClass().getClassLoader(),object.getClass().getInterfaces(),handler);
    }
}
