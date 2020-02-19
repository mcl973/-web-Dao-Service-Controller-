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
                return (BaseInterface) fef.getEnhanceObject(fef.getAnnotationValue(exterFunctionAnnotation));
            }
        }
        return null;
    }

    public Object[] changeParagrame(Method method1,Object[] args){
        //开始参数的改变
        Map<String, String> hasMethodParagrame = fef.isHasMethodParagrame(method1);
        Parameter[] parameters = method1.getParameters();
        Object[] paras = new Object[args.length];

        for (int i = 0; i < parameters.length; i++) {
            if (args[i]!=null){
                if(!((String)args[i]).equals("")){
                    paras[i] = args[i];
                }else if (hasMethodParagrame.containsKey(parameters[i].getName()))
                    paras[i] = hasMethodParagrame.get(parameters[i].getName());
                else paras[i] = args[i];
            }else if(hasMethodParagrame.containsKey(parameters[i].getName())){
                paras[i] = hasMethodParagrame.get(parameters[i].getName());
            }else  paras[i] = args[i];
        }
        return paras;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Method method1 = getOriginMethod(method);
        //修改参数的值
        Object[] finalargs = changeParagrame(method1,args);

        BaseInterface bseInterface = getaopstate(method1);
        Object object = null;
        if (bseInterface!=null) {
            //在这里实现参数注入，及判断args中元素的类型
            object = bseInterface.Excute(method, this.object, finalargs);
        }else {
            //在这里实现参数注入，及判断args中元素的类型
            object = method.invoke(this.object,finalargs);
        }
        return object;
    }
}
