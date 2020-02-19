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
        JieXiAnnotationInterface jxi = null;
        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation annotation : annotations) {
            if ((jxi=getJxi(annotation)) != null)
                break;
        }
        return jxi;
    }

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
        if (method.isAnnotationPresent(Before.class))
            return new JieXiBefore();
        else if (method.isAnnotationPresent(After.class))
            return new JieXinAfter();
        else if (method.isAnnotationPresent(Around.class))
            return new JieXiAround();
        else return new JieXiMySelf();
    }

    public static JieXiAnnotationInterface getJieXi(Parameter parameter){
        if (parameter.isAnnotationPresent(Paragrame.class))
            return new JieXiParagrame();
        return null;
    }

    public static JieXiAnnotationInterface getJieXi(Field field){
        if (field.isAnnotationPresent(Autowrite.class))
            return new JieXiAutowrite();
        return null;
    }
}
