/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: JieXiDao
 * Author:   Administrator
 * Date:     2020/02/18 11:33
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */
package ScannerAndInstance.Instance.JiexiAnnotation;

import Annotation_Collection.Sql.Dao;
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
 * 废弃了，不再使用
 */
public class JieXiDao implements JieXiAnnotationInterface {
    @Override
    public String JiexiAnnotation(Class clazz) {
        if (clazz.isAnnotationPresent(Dao.class)){
            Dao annotation = (Dao)clazz.getAnnotation(Dao.class);
            return annotation.value();
        }else return null;
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
