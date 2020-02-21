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
import MVC_Handle.View.View;
import ScannerAndInstance.AbstractBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        Model object = new GetModel().getmodel(url,req,resp);
        View view = null;
        try {
            //获取view
            view = new View(object,resp.getWriter());
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert view != null;
        //开始渲染
        view.GetResult();
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
