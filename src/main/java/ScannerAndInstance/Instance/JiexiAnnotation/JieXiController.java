/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: JieXiController
 * Author:   Administrator
 * Date:     2020/02/18 11:39
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */
package ScannerAndInstance.Instance.JiexiAnnotation;

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
 * @create 2020/02/18
 * @since 1.0.0
 */
public class JieXiController implements JieXiAnnotationInterface {
    @Override
    public String JiexiAnnotation(Class clazz) {
        if (clazz.isAnnotationPresent(Controller.class)) {
            Controller controller = (Controller) clazz.getAnnotation(Controller.class);
            return controller.value();
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
