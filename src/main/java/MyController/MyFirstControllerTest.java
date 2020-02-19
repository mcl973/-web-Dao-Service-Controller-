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
import MyService.Interface.example;
import SqlInit.AchieveAllTableAndInstance.CreateFile.Dao.md;

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

    @RouteMapping("/test")
    public void  test(HttpServletResponse httpServletResponse,
                      HttpServletRequest httpServletRequest,
                      @Paragrame("name") String name){
        Map<String, md> map= example.show(name);
        if (map !=null){
            String result = "";
            for(Map.Entry<String,md> mdmap:map.entrySet()){
                md value = mdmap.getValue();
                result+="{"+name+":{id:"+value.getid()+"},{name:"+value.getname()+"},{age:"+value.getage()+"}}";
            }
            try {
                PrintWriter writer = httpServletResponse.getWriter();
                writer.write(result);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
