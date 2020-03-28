/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: exampleimpl
 * Author:   Administrator
 * Date:     2020/02/19 10:00
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */
package MyService.impl;

import Annotation_Collection.Aop.After;
import Annotation_Collection.Aop.Before;
import Annotation_Collection.JAutowrite.Autowrite;
import Annotation_Collection.JParagrame.Paragrame;
import Annotation_Collection.JService.Service;
import MyService.Interface.example;
import SqlInit.AchieveAllTableAndInstance.CreateFile.Dao.md;
import SqlInit.AchieveAllTableAndInstance.CreateFile.Dao.mycontext;
import SqlInit.AchieveAllTableAndInstance.CreateFile.Dao.users;
import SqlInit.AchieveAllTableAndInstance.CreateFile.DaoSql.md_sql;
import SqlInit.AchieveAllTableAndInstance.CreateFile.DaoSql.mycontext_sql;
import SqlInit.AchieveAllTableAndInstance.CreateFile.DaoSql.users_sql;

import java.util.Map;
/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Administrator
 * @create 2020/02/19
 * @since 1.0.0
 */
@Service("exampleimpl")
public class exampleimpl implements example {

    @Autowrite("md_sql")
    public md_sql ms;

    @Autowrite("users_sql")
    public users_sql us;

    @Autowrite("mycontext_sql")
    public mycontext_sql mycontext_sql;

    @Override
    @After("MyService.AopMethods.After.AfterAop")
    public Map<String, md> show(@Paragrame("pingan") String name) {
        System.out.println(name);
        Map<String,md> map = null;
        try {
            map =  ms.SelectForname(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    @Before("MyService.AopMethods.Before.BeforeAop2")
    public Map<String, users> login(String name) {
        Map<String, users> stringuserMap = null;
        try {
            stringuserMap = us.SelectForusername(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringuserMap;
    }

    @Override
    public Map<String, mycontext> getAllContext(String name) {
        Map<String, mycontext> stringmycontextMap = null;
        try {
            stringmycontextMap = mycontext_sql.SelectForusername(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringmycontextMap;
    }

    //处理实务操作，失败后回滚
    @After("MyService.AopMethods.After.TranceLation")
    public boolean insert(mycontext mc){
        boolean isok = false;
        try {
            isok = mycontext_sql.insertmethod(mc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isok;
    }

}
