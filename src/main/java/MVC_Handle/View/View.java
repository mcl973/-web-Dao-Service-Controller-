/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: View
 * Author:   Administrator
 * Date:     2020/02/21 10:07
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */
package MVC_Handle.View;

import MVC_Handle.Model.Model;
import MVC_Handle.Model.Models;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Vector;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2020/02/21
 * @since 1.0.0
 */
public class View {
    private Models model ;
    @Deprecated
    private PrintWriter printWriter;
    private HandleHtml html = new HandleHtml();
    public View(Models model){
        this.model=model;
    }
    public View(){

    }

    public Models getModel() {
        return model;
    }

    public void setModel(Models model) {
        this.model = model;
    }

    @Deprecated
    public View(Models model,PrintWriter printWriter){
        this.model=model;
        this.printWriter = printWriter;
    }
    @Deprecated
    public File getView(){
        File view = new File(model.getUrl());
        return view;
    }
    /*
       废弃，这个太麻烦了。。。。。。。。
            我有点明白了，springmvc是如何工作的了，虽然还没有看到源码，但是我好像已经知道了
            1、获取数据，就是在controller里需要做到的事情
            2、在modelandview里写到了网页的名字，在xml文件中写了具体的前缀，合二为一就是文件的完整路径。
            3、将文件读出来并将其以printwriter的形式读到前段。
            4、在读之前函数已经通过参数拿到了之前的数据，如上面的object。
            5、那么就可以通过"\""+object+"\""的形式将数据写入到那里。
            6、所谓的渲染其实就是将网页整个读下来，在读的过程中原本的定义的毫无逻辑可言的名字就变得可以理解了。
            7、例子：这个是定义在jsp中的文件，一开始我以为是通过servlet的形式传递过去，其实并不是的，而是传递过去整个html文件

                    <form action="${basePath}" method="post" enctype="application/x-www-form-urlencoded">
                        <table border="2">
                            <tr>
                                <td>id:<input type="text" name="id"value="${items.id}"></td>
                                <td>name:<input type="text" name="name"value="${items.name}"></td>
                                <td>price:<input type="text" name="price"value="${items.price}"></td>
                                <td>pic:<input type="text" name="pic"value="${items.pic}"></td>
                                <td>detail:<input type="text" name="detail"value="${items.detail}"></td>
                                <td>createtime:<input type="text" name="createtime"value="${items.createtime}"></td>
                                <td><input type="submit" value="修改/更新"></td>
                            </tr>
                        </table>
                    </form>

                  如：
                    PrintWriter writer = resp.getWriter();
                  public void 重新渲染(Item items,PrintWriter writer){
                        writer.write("<form action="${basePath}" method="post" enctype="application/x-www-form-urlencoded">");
                        writer.write("<table border="2">");
                        writer.write(" <tr>");                                        //此时数据已经卸载了这里面，哈哈哈我他喵真是天才
                        writer.write("<td>id:<input type=\"text\" name=\"id\"value=\""+${items.id}+"\"></td>");
                        ......
                        writer.flush();
                        writer.close();
                  }
         */
    //废弃
    @Deprecated
    public void GetResult(){
        try {
            if (model!=null) {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(getView()));
                //开始读取html文件
                String str = bufferedReader.readLine();
                String result = null;
                //获取map中的key和value
                Object[] object = null;
                String objectname = null;
                //这里仅仅是测试使用，后期还是要做改动
                //这里只写了一个,其实原理一样只需要针对html文件去处理就好了
                for (Map.Entry<String, Object[]> map : model.getModel().entrySet()) {
                    object = map.getValue();
                    objectname = map.getKey();
                    break;
                }
                while (str != null) {
                    //具体的修改文件
                    String temp = html.wrapetheresult(str, object[0], objectname);
                    if (temp != null)
                        result += temp;
                    str = bufferedReader.readLine();
                }
                if (result != null) {
                    //去除null值
                    String aNull = result.replaceAll("null", "");
                    //将其渲染到浏览器
                    printWriter.write(aNull);
                    printWriter.flush();
                }
            }else {
                //如果model为null，表示没有具体的值或是操作失败
                printWriter.write("null");
                printWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            printWriter.close();
        }
    }

    public void GetResult(HttpServletRequest req, HttpServletResponse resp){
        Map<String, Object[]> model = this.model.getModel();
        //将数据放在request中，通过dispatcher的方式跳转到某个页面，此时那个页面通过request就可以
        //那渠道相应的数据了
        exposetoRequest(model,req);
        //这是一个ApplicationDispatcher实例
        RequestDispatcher dispatcher = getRequestDispatcher(this.model.getRedircturl(),req);
//        changeDispatcher_URI_ServletPath(dispatcher.getClass(),dispatcher);
        if (dispatcher != null) {
            try {
                dispatcher.forward(req,resp);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    public RequestDispatcher getRequestDispatcher(String url,HttpServletRequest req){
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(url);
        changeDispatcher_URI_ServletPath(requestDispatcher);
        return requestDispatcher;
    }


    public void changeDispatcher_URI_ServletPath(RequestDispatcher requestDispatcher){
        try {
            Class<? extends RequestDispatcher> clazz = requestDispatcher.getClass();
            Field requestURI = clazz.getDeclaredField("requestURI");
            requestURI.setAccessible(true);
            Field servletPath = clazz.getDeclaredField("servletPath");
            servletPath.setAccessible(true);
            String requesturl = (String)requestURI.get(requestDispatcher);
            if (isLink(requesturl)){
                String[] split = requesturl.split("/");
                String s = WrapLink(split);
                if (!s.equals(""))
                    requestURI.set(requestDispatcher,s);
            }
            String servletpath = (String)servletPath.get(requestDispatcher);
            if (isLink(servletpath)){
                String[] split = servletpath.split("/");
                String s = WrapLink(split);
                if (!s.equals(""))
                    servletPath.set(requestDispatcher,s);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    //对于放在WEB-INF文件夹下的文件，需要重新定义连接
    public String WrapLink(String[] links){
        String result = "";
        int StartToWrap = 0;
        for(int i=0;i<links.length-1;i++) {
            if (links[i].equals("WEB-INF")){
                StartToWrap = 1;
            }
            if (StartToWrap == 1) {
                if (links[i].equals("WEB-INF"))
                    result += "/"+links[i] + "/";
                else
                    result += links[i] + "/";
            }
        }
        result+=links[links.length-1];
        return result;
    }

    public boolean isLink(String weblink){
        if (weblink.contains("WEB-INF"))
            return true;
        else return false;
    }
    /*
        将数据提供给request
     */
    public void exposetoRequest(Map<String, Object[]> model,HttpServletRequest req){
        for(Map.Entry<String,Object[]> map:model.entrySet()){
            if (map.getValue()!=null){
                Vector<Object> object = new Vector<>();
                Object[] value = map.getValue();
                for (Object o : value) {
                    object.add(o);
                }
                req.setAttribute(map.getKey(),object);
            }
        }
    }
}
