/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: HandleRouteMapping
 * Author:   Administrator
 * Date:     2020/02/19 16:02
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */
package ScannerAndInstance.HandleRouteMappEvent;

import ScannerAndInstance.Instance.GetJieXi;
import ScannerAndInstance.Instance.JiexiAnnotation.JieXiRouteMapping;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2020/02/19
 * @since 1.0.0
 */
public class HandleRouteMapping extends AbstractHandleRoutemapping {
    @Override
    void instanceRoutemapping() {
        //其实应该分的再细一点
        //如Dao、DaoSql一个map，Service一个map，Controller和路径映射一个map
        //最后将这些都放入一个大的map里，或是不放置。
        //但是这样的话就不能够在注入的时候区分哪些东西在哪里
        Map<String,Object> method_Object = new HashMap<>();

        for(Map.Entry<String ,Object> map:iocmap.entrySet()){
            Object value = map.getValue();
            Class<?> aClass = value.getClass();
            JieXiRouteMapping jieXiRouteMapping = new JieXiRouteMapping();
            //先获取类上的路径
            String s = jieXiRouteMapping.JiexiAnnotation(aClass);
            if(s!=null){
                Method[] declaredMethods = aClass.getDeclaredMethods();
                for (Method declaredMethod : declaredMethods) {
                    //再获取类上的路径
                    String s1 = jieXiRouteMapping.JiexiAnnotation(declaredMethod);
                        if (s1!=null){
                        method_Object.put(s+s1,declaredMethod);
                        method_Object.put(declaredMethod.hashCode()+"",map.getKey());
                    }
                }
            }
        }
        for(Map.Entry<String ,Object > map:method_Object.entrySet()){
            iocmap.put(map.getKey(),map.getValue());
        }
    }
}
