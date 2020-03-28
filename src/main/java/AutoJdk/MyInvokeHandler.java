/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: MyInvokeHandler
 * Author:   Administrator
 * Date:     2020/02/19 10:35
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */
package AutoJdk;

import Annotation_Collection.Aop.After;
import Annotation_Collection.Aop.Around;
import Annotation_Collection.Aop.Before;
import MyService.AopMethods.BaseInterface;

import java.io.ObjectInputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2020/02/19
 * @since 1.0.0
 */
public class MyInvokeHandler extends MyHandler implements InvocationHandler {
    public MyInvokeHandler(Object object){
        super(object);
    }
    public BaseInterface getaopstate(Method method){
        if (method!=null) {
            if (method.isAnnotationPresent(Around.class)) {
                return fef.getlooparound(method);
            }else if (method.isAnnotationPresent(After.class)){
                return fef.getafter(method);
            } else if (method.isAnnotationPresent(Before.class)) {
                return fef.getbefore(method);
            }else{
                Annotation exterFunctionAnnotation = fef.getExterFunctionAnnotation(method);
                if (exterFunctionAnnotation == null)
                    return null;
                return (BaseInterface) fef.getEnhanceObject(fef.getAnnotationValue(exterFunctionAnnotation));
            }
        }
        return null;
    }

    //此时执行的是参数的自动注入，如果传进来的值不为空，则不进行自动注入
    public Object[] changeParagrame(Method method1,Object[] args){
        //开始参数的改变
        Map<String, String> hasMethodParagrame = fef.isHasMethodParagrame(method1);
        Parameter[] parameters = method1.getParameters();
        if(args!=null) {//参数不为空在进行参数改变，否则之间返回null
            Object[] paras = new Object[args.length];

            for (int i = 0; i < parameters.length; i++) {
                if (args[i] != null) {
                    //如果其类型为String则判断其值是不是为空
                    if (!((String) args[i]).equals("")) {
                        paras[i] = args[i];
                    } else if (hasMethodParagrame.containsKey(parameters[i].getName()))//查看这个参数有没有注解
                        paras[i] = hasMethodParagrame.get(parameters[i].getName());
                    else paras[i] = args[i];
                } else if (hasMethodParagrame.containsKey(parameters[i].getName())) {
                    paras[i] = hasMethodParagrame.get(parameters[i].getName());
                } else paras[i] = args[i];
            }
            return paras;
        }else return null;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Method method1 = getOriginMethod(method);
        //修改参数的值
        Object[] finalargs = changeParagrame(method1,args);

        BaseInterface bseInterface = getaopstate(method1);
        Object object = null;
        if (bseInterface!=null) {
            //在这里实现了方法的aop的操作
            object = bseInterface.Excute(method, this.object, finalargs);
        }else {
            //如果方法没有aop那就执行正常的操作
            object = method.invoke(this.object,finalargs);
        }
        return object;
    }
}
