/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: JieXiAutowrite
 * Author:   Administrator
 * Date:     2020/02/19 8:49
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */
package ScannerAndInstance.Instance.JiexiAnnotation;

import Annotation_Collection.JAutowrite.Autowrite;
import Annotation_Collection.JController.Controller;
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
public class JieXiAutowrite implements JieXiAnnotationInterface {
    @Override
    public String JiexiAnnotation(Class clazz) {
        return null;
    }

    @Override
    public String JiexiAnnotation(Method method) {
        return null;
    }

    @Override
    public String JiexiAnnotation(Field field) {
        if (field.isAnnotationPresent(Autowrite.class)) {
            Autowrite autowrite = (Autowrite) field.getAnnotation(Autowrite.class);
            return autowrite.value();
        }else return null;
    }

    @Override
    public String JiexiAnnotation(Parameter parameter) {
        return null;
    }

}
