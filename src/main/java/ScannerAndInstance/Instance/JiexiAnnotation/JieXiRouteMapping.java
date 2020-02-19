/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: JieXiRouteMapping
 * Author:   Administrator
 * Date:     2020/02/19 9:45
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */
package ScannerAndInstance.Instance.JiexiAnnotation;

import Annotation_Collection.RouteMap.RouteMapping;
import ScannerAndInstance.Instance.JieXiAnnotationInterface;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
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
public class JieXiRouteMapping implements JieXiAnnotationInterface {
    @Override
    public String JiexiAnnotation(Class clazz) {
        if (clazz.isAnnotationPresent(RouteMapping.class)){
            RouteMapping annotation = (RouteMapping)clazz.getAnnotation(RouteMapping.class);
            return annotation.value();
        }
        return null;
    }

    @Override
    public String JiexiAnnotation(Method method) {
        if (method.isAnnotationPresent(RouteMapping.class)){
            RouteMapping annotation = method.getAnnotation(RouteMapping.class);
            return annotation.value();
        }
        return null;
    }

    @Override
    public String JiexiAnnotation(Field field) {
        return null;
    }

    @Override
    public String JiexiAnnotation(Parameter parameter) {
        return null;
    }
}
