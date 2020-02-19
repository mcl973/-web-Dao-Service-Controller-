/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: Dispatch
 * Author:   Administrator
 * Date:     2020/02/19 12:09
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */
package ScannerAndInstance;

import ScannerAndInstance.HandleAutowriteEvent.HandleAutowrite;
import ScannerAndInstance.HandleRouteMappEvent.HandleRouteMapping;
import ScannerAndInstance.HanleAopEvent.HandleAopBean;
import ScannerAndInstance.Instance.GetJieXi;
import ScannerAndInstance.Instance.InstanceBean;
import ScannerAndInstance.Instance.JieXiAnnotationInterface;
import ScannerAndInstance.Scanner.Scanner;
import SqlInit.SqlFind.MySqlInit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Parameter;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2020/02/19
 * @since 1.0.0
 */
public class Dispatch extends HttpServlet {
    private static Map<String,Object> map = null;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String urlrequest = req.getRequestURI();
        String context = req.getContextPath();
        String url = urlrequest.replace(context, "");
        //通过url获取到具体的method方法。
        if (map.containsKey(url)) {
            Method method = (Method) map.get(url);
            //通过方法的hashcode获取到具体的类。
            Object o = map.get((String) map.get(method.hashCode() + ""));
            //获取到req中的参数
            Object[] paragrame = getParagrames(req, resp, method);
            try {
                method.invoke(o, paragrame);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void init() throws ServletException {
        //这个后续可以放入文件中去读取
        String[] sqlparam = {"com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/mao",
                "root","root"};
        new MySqlInit(sqlparam);

        //扫描
        new Scanner();
        //实例化类
        new InstanceBean();
        //处理aop
        new HandleAopBean();
        //处理自动注入
        new HandleAutowrite();
        //处理路径和方法的映射
        new HandleRouteMapping();
        //获取ioc容器
        map = AbstractBean.iocmap;
    }


    public Object[] getParagrames(HttpServletRequest req, HttpServletResponse resp,Method method){
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

    public String getParagrameName(Parameter parameter){
        JieXiAnnotationInterface jieXi = GetJieXi.getJieXi(parameter);
        if (jieXi!=null)
            return jieXi.JiexiAnnotation(parameter);
        else return null;
    }

    public Dispatch(){
//        new Scanner();
//        new InstanceBean();
//        new HandleAopBean();
//        new HandleAutowrite();
        //后续还会加上一个关于RouteMapping的实现，这个明天再写

    }
}
