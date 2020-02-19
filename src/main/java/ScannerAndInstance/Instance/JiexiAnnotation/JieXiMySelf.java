/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: JieXiMySelf
 * Author:   Administrator
 * Date:     2020/02/18 11:42
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */
package ScannerAndInstance.Instance.JiexiAnnotation;

import Annotation_Collection.RouteMap.RouteMapping;
import ScannerAndInstance.Instance.JieXiAnnotationInterface;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2020/02/18
 * @since 1.0.0
 */
public class JieXiMySelf implements JieXiAnnotationInterface {
    @Override
    public String JiexiAnnotation(Class clazz) {
        return null;
    }

    @Override
    public String JiexiAnnotation(Method clazz) {
        Annotation[] annotations = clazz.getAnnotations();
        String values = "";
        for (Annotation annotation : annotations) {
            if (annotation instanceof Override)
                continue;
            values+=getValueMethodreturn(annotation,findvaluemethod(annotation.getClass()));
            values+="myself";
        }
        if (!values.equals(""))
            return values;
        else return null;
    }

    @Override
    public String JiexiAnnotation(Field clazz) {
        return null;
    }

    @Override
    public String JiexiAnnotation(Parameter clazz) {
        return null;
    }

    public String getValueMethodreturn(Annotation o,Method method){
        try {
            return (String)method.invoke(o,null);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Method findvaluemethod(Class clazz){
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (method.getName().equals("value"))
                return method;
        }
        return null;
    }
}
