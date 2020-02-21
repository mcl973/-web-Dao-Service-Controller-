/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: MyHandler
 * Author:   Administrator
 * Date:     2020/02/19 10:37
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */
package AutoJdk;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2020/02/19
 * @since 1.0.0
 */
public class MyHandler {
    protected static FindEnhanceFunction fef = FindEnhanceFunction.getInstance();
    public Object object;
    public MyHandler(Object object){
        this.object = object;
    }

    //通过代理类和原始类的对比得出原始类中的需要被增强的函数
    /*
        对比的数据包括：
            1.返回值
            2.函数名
            3.参数类型
     */
    public Method getOriginMethod(Method method1){
        int k = 0;
        Class clazz = this.object.getClass();
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (method.getName().equals(method1.getName())) {
                Parameter[] parameters = method.getParameters();
                Parameter[] parameters1 = method1.getParameters();
                if (parameters.length == parameters1.length) {

                    for (int i = 0; i < parameters.length; i++) {
                        if (parameters[i].getType() != parameters1[i].getType())
                            k = 1;
                    }
                    if (k == 1)
                        continue;
                    if (method.getReturnType() != method1.getReturnType()) {
                        continue;
                    }
                }else continue;
                return method;
            }
        }
        return null;
    }
}
