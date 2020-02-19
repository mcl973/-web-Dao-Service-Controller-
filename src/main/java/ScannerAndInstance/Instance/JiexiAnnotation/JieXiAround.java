/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName:
 * Author:   Administrator
 * Date:     2020/02/19 9:42
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */
package ScannerAndInstance.Instance.JiexiAnnotation;

import Annotation_Collection.Aop.Around;
import ScannerAndInstance.Instance.JieXiAnnotationInterface;

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
public class JieXiAround implements JieXiAnnotationInterface {
    @Override
    public String JiexiAnnotation(Class clazz) {
        return null;
    }

    @Override
    public String JiexiAnnotation(Method method) {
        if (method.isAnnotationPresent(Around.class)) {
            Around annotation = method.getAnnotation(Around.class);
            return annotation.value();
        }else
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
