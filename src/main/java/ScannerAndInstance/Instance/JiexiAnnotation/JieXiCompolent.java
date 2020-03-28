/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: JieXiCompolent
 * Author:   Administrator
 * Date:     2020/03/28 12:15
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */
package ScannerAndInstance.Instance.JiexiAnnotation;

import Annotation_Collection.NormalBean.Compolent;
import ScannerAndInstance.Instance.JieXiAnnotationInterface;

import javax.annotation.processing.Completion;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2020/03/28
 * @since 1.0.0
 */
public class JieXiCompolent implements JieXiAnnotationInterface {
    @Override
    public String JiexiAnnotation(Class clazz) {
        if(clazz.isAnnotationPresent(Compolent.class)) {
            Compolent compolent = (Compolent) clazz.getAnnotation(Compolent.class);
            return compolent.value();
        }else return null;
    }

    @Override
    public String JiexiAnnotation(Method method) {
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
