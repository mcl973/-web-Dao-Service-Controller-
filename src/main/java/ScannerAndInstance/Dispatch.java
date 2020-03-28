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

import AllConfigure.Sql.MySqlParagrame;
import MVC_Handle.HandleSevlet.HandlePost.Handle_Post;
import ScannerAndInstance.HandleAutowriteEvent.HandleAutowrite;
import ScannerAndInstance.HandleRouteMappEvent.HandleRouteMapping;
import ScannerAndInstance.HanleAopEvent.HandleAopBean;
import ScannerAndInstance.Instance.InstanceBean;
import ScannerAndInstance.Scanner.Scanner;
import SqlInit.AbstractSql;
import SqlInit.SqlFind.MySqlInit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2020/02/19
 * @since 1.0.0
 */
public class Dispatch extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //处理post事件
        Handle_Post handle_post = new Handle_Post(req, resp);
        handle_post.handle();

    }

    /*
        初始化--》 数据库连接、扫描、实例化、aop操作、自动注入、路径映射
     */
    @Override
    public void init() throws ServletException {
        //获取sql的参数
        MySqlParagrame mySqlParagrame = new MySqlParagrame();
        new MySqlInit(mySqlParagrame.getSqlparam());
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
    }

    public Dispatch(){
//        new Scanner();
//        new InstanceBean();
//        new HandleAopBean();
//        new HandleAutowrite();
        //后续还会加上一个关于RouteMapping的实现，这个明天再写

    }

    //最终将数据销毁
    @Override
    public void destroy() {
        try {
            AbstractSql.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            AbstractSql.statement.closeOnCompletion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
