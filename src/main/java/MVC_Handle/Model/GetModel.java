/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: GetModel
 * Author:   Administrator
 * Date:     2020/02/21 14:14
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */
package MVC_Handle.Model;

import ScannerAndInstance.AbstractBean;
import ScannerAndInstance.Instance.GetJieXi;
import ScannerAndInstance.Instance.JieXiAnnotationInterface;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2020/02/21
 * @since 1.0.0
 */
public class GetModel {

    public Model getmodel(String url,HttpServletRequest req, HttpServletResponse resp) {
        Method method = (Method) AbstractBean.getiocmap_Value(url);
        //通过方法的hashcode获取到具体的类。
        Object o = AbstractBean.getiocmap_Value((String) AbstractBean.getiocmap_Value(method.hashCode() + ""));
        //获取到req中的参数
        Object[] paragrame = getParagrames(req, resp, method);
        try {
            Model object = (Model) method.invoke(o, paragrame);
            return object;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private Object[] getParagrames(HttpServletRequest req, HttpServletResponse resp, Method method){
        Parameter[] parameters = method.getParameters();
        Object[] object = new Object[parameters.length];
        //待会这里会有一个参数类的注解，将注解中与现存好的值，放入没有输入的具体参数中。
        //即某个参数如String类型的为null，Object为null等等
        //如果为这种现象那么就直接是自动的填充预先写好的放在注解中的数据

        //还需要将http参数传递过去
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            String typeName = parameter.getParameterizedType().getTypeName();
            if (typeName.contains("HttpServletRequest"))
                object[i] = req;
            else if (typeName.contains("HttpServletResponse"))
                object[i] = resp;
            else {
                String paragrameName = getParagrameName(parameter);
                if (paragrameName!=null)
                    object[i] = req.getParameter(paragrameName);
                else object[i] =null;
            }
        }
        return object;
    }
    private String getParagrameName(Parameter parameter){
        JieXiAnnotationInterface jieXi = GetJieXi.getJieXi(parameter);
        if (jieXi!=null)
            return jieXi.JiexiAnnotation(parameter);
        else return null;
    }

}
