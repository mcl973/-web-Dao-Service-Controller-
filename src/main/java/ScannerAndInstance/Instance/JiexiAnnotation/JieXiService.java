/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: JieXiService
 * Author:   Administrator
 * Date:     2020/02/18 11:40
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */
package ScannerAndInstance.Instance.JiexiAnnotation;

import Annotation_Collection.JService.Service;
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
 * @create 2020/02/18
 * @since 1.0.0
 */
public class JieXiService implements JieXiAnnotationInterface {
    @Override
    public String JiexiAnnotation(Class clazz) {
        if (clazz.isAnnotationPresent(Service.class)){
            Service annotation = (Service)clazz.getAnnotation(Service.class);
            return annotation.value();
        }else
        return null;
    }

    @Override
    public String JiexiAnnotation(Method clazz) {
        return null;
    }

    @Override
    public String JiexiAnnotation(Field clazz) {
        return null;
    }

    @Override
    public String JiexiAnnotation(Parameter clazz) {
        return null;
    }
}
