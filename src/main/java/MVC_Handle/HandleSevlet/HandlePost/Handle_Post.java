/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: Handle_Post
 * Author:   Administrator
 * Date:     2020/02/21 14:27
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */
package MVC_Handle.HandleSevlet.HandlePost;

import MVC_Handle.Model.GetModel;
import MVC_Handle.Model.Model;
import MVC_Handle.Model.Models;
import MVC_Handle.View.View;
import ScannerAndInstance.AbstractBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2020/02/21
 * @since 1.0.0
 */
public class Handle_Post implements PostMethods{
    private HttpServletRequest req;
    private HttpServletResponse resp;

    public Handle_Post(HttpServletRequest req, HttpServletResponse resp){
        this.req=req;
        this.resp = resp;
    }

    public void handle(){
        String url = achieveObject_key(req);
        //将索取图片ioc的url除去
        if (AbstractBean.getiocmap_Value(url)==null)
            return;
        //通过url获取到具体的method方法。
        /*
            第一种设置数据，每一个用户一个session
            HttpSession session = req.getSession();
            session.setAttribute("nihao",new Object());
            jsp端通过session.getAttribute("nihao);来获取具体的数据

            第二种设置数据，每一个session中都有一个上下文
            ServletContext servletContext = req.getSession().getServletContext();
            servletContext.setAttribute("nihao",new Object());
            application.getAttribute("nihao");来获取具体的数据

            第三种设置数据，将数据放在request中
            也是本程序所使用放的方式。
        */
        Models object = new GetModel().getmodel(url,req,resp);
        if (object != null) {
            View view = new View(object);
            if (!object.isMapModelNull()) {
                //开始渲染
                view.GetResult(req,resp);
            }else if (!object.isUrlNull()){
                try {
                    String redircturl = view.getModel().getRedircturl();
                    if (redircturl.contains("WEB-INF")) {
                        RequestDispatcher requestDispatcher = view.getRequestDispatcher(redircturl, req);
                        requestDispatcher.forward(req, resp);
                    }else {
                        resp.sendRedirect(redircturl);
                    }
                } catch (IOException | ServletException e) {
                    e.printStackTrace();
                }
            }else {
                try {
                    resp.getWriter().write("null");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else {
            try {
                resp.getWriter().write("null");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String achieveObject_key(HttpServletRequest req){
        //获取127.0.0.1:8080/ 后面的路径
        String urlrequest = req.getRequestURI();
        String context = req.getContextPath();
        String url = urlrequest.replace(context, "");
        return url;
    }

}
