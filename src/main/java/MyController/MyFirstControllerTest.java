/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: MyFirstControllerTest
 * Author:   Administrator
 * Date:     2020/02/19 10:14
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */
package MyController;

import Annotation_Collection.JAutowrite.Autowrite;
import Annotation_Collection.JController.Controller;
import Annotation_Collection.JParagrame.Paragrame;
import Annotation_Collection.RouteMap.RouteMapping;
import MVC_Handle.Model.Model;
import MVC_Handle.Model.Models;
import MyService.Interface.example;
import SqlInit.AchieveAllTableAndInstance.CreateFile.Dao.md;
import SqlInit.AchieveAllTableAndInstance.CreateFile.Dao.mycontext;
import SqlInit.AchieveAllTableAndInstance.CreateFile.Dao.user;
import SqlInit.AchieveAllTableAndInstance.CreateFile.Dao.users;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2020/02/19
 * @since 1.0.0
 */
@Controller("MyFirstControllerTest")
@RouteMapping("/controller")
public class MyFirstControllerTest {

    @Autowrite("exampleimpl")
    public example example;
    /*
        针对于Springmvc应该是使用了HttpServletResponse的重定向
        然后使用HttpServletResponse的信息填充将数据填充进去
     */
    @RouteMapping("/test")
    public Models   test(HttpServletResponse httpServletResponse,
                      HttpServletRequest httpServletRequest,
                      @Paragrame("name") String name){
        Map<String, md> map= example.show(name);
        if (map !=null){

            String result = "";
            Models model = new Models();
            if (map.size()==0)
                model = null;
            else {
                for (Map.Entry<String, md> mdmap : map.entrySet()) {
                    md value = mdmap.getValue();
                    Object[] objects = {value};
                    model.setModel("mds", objects);
                }
                model.setUrl("index");
            }
            return model;
        }
        return null;
    }
    @RouteMapping("/login")
    public Models   login(){
        Models model = new Models();
        model.setUrl("login");
        return model;
    }
    @RouteMapping("/logining")
    public Models   logining(HttpServletResponse httpServletResponse,
                        HttpServletRequest httpServletRequest,
                        @Paragrame("name") String name){
        Models model = new Models();
        model.setUrl("DataShow");
        Map<String, users> login = example.login(name);
        Map<String, mycontext> allContext = example.getAllContext(name);
        mycontext[] mycontexts = new mycontext[allContext.size()];
        int index = 0;
        for(Map.Entry<String,mycontext> map:allContext.entrySet())
            mycontexts[index++] = map.getValue();

        model.setModel("object",mycontexts);
        if (login.size()>0)
            return model;
        else return null;
    }
}
