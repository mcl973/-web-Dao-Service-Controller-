/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: JieXiParagrame
 * Author:   Administrator
 * Date:     2020/02/18 11:41
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */
package ScannerAndInstance.Instance.JiexiAnnotation;

import Annotation_Collection.JParagrame.Paragrame;
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
public class JieXiParagrame implements JieXiAnnotationInterface {
    @Override
    public String JiexiAnnotation(Class clazz) {
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
        if (clazz.isAnnotationPresent(Paragrame.class)){
            Paragrame annotation = (Paragrame)clazz.getAnnotation(Paragrame.class);
            return annotation.value();
        }else
            return null;
    }
}
