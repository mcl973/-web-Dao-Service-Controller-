/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: HandleAutowrite
 * Author:   Administrator
 * Date:     2020/02/19 11:02
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */
package ScannerAndInstance.HandleAutowriteEvent;
import ScannerAndInstance.Instance.GetJieXi;
import ScannerAndInstance.Instance.JieXiAnnotationInterface;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;
import java.util.Objects;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2020/02/19
 * @since 1.0.0
 */
public class HandleAutowrite extends AbstractHandleAutowrite {
    @Override
    public void doAutowrite() {
        for(Map.Entry<String,Object> map:iocmap.entrySet()) {
            Object value = map.getValue();
            Class<?> clazz = value.getClass();
            String vs = null;
            //自动注入field
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                vs = isHasFieldAutowrite(field);
                //如果vs不是为null那么就将创建这个实例
                if (vs!=null){
                    field.setAccessible(true);
                    try {
                        field.set(value,iocmap.get(vs));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public String isHasFieldAutowrite(Field field){
        JieXiAnnotationInterface jieXi = GetJieXi.getJieXi(field);
        if (jieXi!=null)
            return jieXi.JiexiAnnotation(field);
        else return null;
    }

}
