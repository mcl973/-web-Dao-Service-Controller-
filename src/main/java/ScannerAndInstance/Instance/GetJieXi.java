/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: GetJieXi
 * Author:   Administrator
 * Date:     2020/02/18 11:47
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */
package ScannerAndInstance.Instance;

import Annotation_Collection.Aop.After;
import Annotation_Collection.Aop.Around;
import Annotation_Collection.Aop.Before;
import Annotation_Collection.JAutowrite.Autowrite;
import Annotation_Collection.JController.Controller;
import Annotation_Collection.JParagrame.Paragrame;
import Annotation_Collection.JService.Service;
import Annotation_Collection.RouteMap.RouteMapping;
import Annotation_Collection.Sql.Dao;
import Annotation_Collection.Sql.DaoSql;
import ScannerAndInstance.Instance.JiexiAnnotation.*;

import java.beans.beancontext.BeanContext;
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
public class GetJieXi {
    //应该在后续的过程中添加关于多注解的解释
    public static JieXiAnnotationInterface getJieXi(Class clazz){
        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation instanceof Dao)
                return new JieXiDao();
            else if(annotation instanceof DaoSql)
                return new JieXiDaoSql();
            else if (annotation instanceof Service)
                return new JieXiService();
            else if (annotation instanceof Controller)
                return new JieXiController();
        }
        return null;
    }

    @Deprecated
    private static JieXiAnnotationInterface getJxi(Annotation annotation){
        if (annotation instanceof Dao)
            return new JieXiDao();
        else if(annotation instanceof DaoSql)
            return new JieXiDaoSql();
        else if (annotation instanceof Service)
            return new JieXiService();
        else if (annotation instanceof Controller)
            return new JieXiController();
        return null;
    }

    public static JieXiAnnotationInterface getJieXi(Method method){
        Annotation[] annotations = method.getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation instanceof Before)
                return new JieXiBefore();
            else if (annotation instanceof After)
                return new JieXinAfter();
            else if (annotation instanceof Around)
                return new JieXiAround();
            else {
                if (JieXiMySelf.MySelfAnnotation(annotation))
                    return new JieXiMySelf(annotation);
            }
        }
        return null;
    }

    public static JieXiAnnotationInterface getJieXi(Parameter parameter){
        Annotation[] annotations = parameter.getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation instanceof Paragrame)
                return new JieXiParagrame();
        }
        return null;
    }

    public static JieXiAnnotationInterface getJieXi(Field field){
        Annotation[] annotations = field.getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation instanceof Autowrite)
                return new JieXiAutowrite();
        }
        return null;
    }
}
