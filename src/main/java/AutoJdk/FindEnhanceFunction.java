/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: FindEnhanceFunction
 * Author:   Administrator
 * Date:     2020/02/19 10:40
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */
package AutoJdk;

import Annotation_Collection.Aop.After;
import Annotation_Collection.Aop.Around;
import Annotation_Collection.Aop.Before;
import Annotation_Collection.RouteMap.RouteMapping;
import MyService.AopMethods.BaseInterface;
import ScannerAndInstance.Instance.GetJieXi;
import ScannerAndInstance.Instance.JieXiAnnotationInterface;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2020/02/19
 * @since 1.0.0
 */
public class FindEnhanceFunction {
    private static FindEnhanceFunction findEnhanceFunction = new FindEnhanceFunction();
    private FindEnhanceFunction(){

    }
    //单例模式
    public static FindEnhanceFunction getInstance(){
        return findEnhanceFunction;
    }

    public BaseInterface getlooparound(Method method){
        /*
           这里就需要选择出是哪一个增强类了
           通过looparound中的value可以得到
         */
        Around la = method.getAnnotation(Around.class);
        //得到增强方法的类全限定名
        String value = la.value();
        return (BaseInterface) getMyAnnotation(value);
    }


    public BaseInterface getafter(Method method){
        /*
           这里就需要选择出是哪一个增强类了
           通过looparound中的value可以得到
         */
        After la = method.getAnnotation(After.class);
        //得到增强方法的类全限定名
        String value = la.value();
        return (BaseInterface) getMyAnnotation(value);
    }

    public BaseInterface getbefore(Method method){
        /*
           这里就需要选择出是哪一个增强类了
           通过looparound中的value可以得到
         */
        Before la = method.getAnnotation(Before.class);
        //得到增强方法的类全限定名
        String value = la.value();
        return (BaseInterface) getMyAnnotation(value);
    }

    //查看这个方法是否有注解参数
    public Map<String,String> isHasMethodParagrame(Method method){
        //自动注入paragrame
        String value = null;
        Map<String,String> map = new HashMap<>();
        Parameter[] parameters = method.getParameters();
        for (Parameter parameter : parameters) {
            value = isHasParagrameAutowrite(parameter);
            if (value!=null){
                map.put(parameter.getName(),value);
            }
        }
        return map;
    }

    //这个参数是否支持自动注入，如果支持则返回其上的注解中的值，否则返回null
    public String isHasParagrameAutowrite(Parameter parameter){
        JieXiAnnotationInterface jieXi = GetJieXi.getJieXi(parameter);
        if (jieXi != null)
            return jieXi.JiexiAnnotation(parameter);
        return null;
    }

    //获取带有value函数的注解
    private Annotation getExterAnnotation(Annotation[] annotations){
        if (annotations.length != 0) {
            for (Annotation annotation : annotations) {
                //需要修改,添加下面的这段话因为目前函数上需要派出的就是这个了。
                //RouteMapping是一个提供链接的注解类，所以需要排除
                if (annotation instanceof RouteMapping){
                    continue;
                }
                Class annclazz = annotation.getClass();
                Method[] methods = annclazz.getMethods();
                for (Method method1 : methods) {
                    if (method1.getName().equals("value")) {
                        return annotation;
                    }
                }
            }
        }
        return null;
    }

    public Annotation getExterAnnotation2(Annotation[] annotations){
        return getExterAnnotation(annotations);
    }

    /*
        得到函数上的注解
     */
    public Annotation getExterFunctionAnnotation(Method method) {
        Annotation[] annotations = method.getAnnotations();
        return getExterAnnotation(annotations);
    }

    /*
        得到类属性上的注解
     */
    public Annotation getExterFieldAnnotation(Field field) {
        Annotation[] annotations = field.getAnnotations();
        return getExterAnnotation(annotations);
    }

    /*
        得到类上的注解
     */
    public Annotation getExterClassAnnotation(Class clazz) {
        Annotation[] annotations = clazz.getAnnotations();
        return getExterAnnotation(annotations);
    }

    /*
        得到自定义注解中的value函数中的值
     */
    public String getAnnotationValue(Annotation getmethod){
        assert getmethod != null;
        Class<? extends Annotation> aClass = getmethod.getClass();
        Method[] methods = aClass.getMethods();
        Method method1 = null;
        for (Method method : methods) {
            if (method.getName().equals("value")) {
                method1 = method;
                break;
            }
        }
        if (method1!=null) {
            String name = null;
            try {
                name = (String)method1.invoke(getmethod, null);
                return name;
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /*
        对外开放的函数
     */
    public Object getEnhanceObject(String value){
        return getMyAnnotation(value);
    }

    /*
        快速的获取到注释中的value函数中的值
     */
    public String getValue(Class clazz){
        Annotation exterClassAnnotation = getExterClassAnnotation(clazz);
        return getAnnotationValue(exterClassAnnotation);
    }
    //快速的获取method上面的注解对应的value值
    public String getValue(Method method){
        Annotation exterFunctionAnnotation = getExterFunctionAnnotation(method);
        return getAnnotationValue(exterFunctionAnnotation);
    }

    //快速的获取field上面的注解对应的value值
    public String getValue(Field field){
        Annotation exterFieldAnnotation = getExterFieldAnnotation(field);
        return getAnnotationValue(exterFieldAnnotation);
    }

    private Object getMyAnnotation(String value){
        /*
            通过value查找具体的类
         */
        Object around = null;
        try {
            Class clazz = Class.forName(value);
            around = clazz.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return around;
    }
}
