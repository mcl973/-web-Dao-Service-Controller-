///**
// * Copyright (C), 2015-2020, XXX有限公司
// * FileName: exampleimpl
// * Author:   Administrator
// * Date:     2020/02/19 10:00
// * Description:
// * History:
// * <author>          <time>          <version>          <desc>
// */
//package MyService.impl;
//
//import Annotation_Collection.Aop.Before;
//import Annotation_Collection.JAutowrite.Autowrite;
//import Annotation_Collection.JParagrame.Paragrame;
//import Annotation_Collection.JService.Service;
//import MyService.Interface.example;
//import SqlInit.AchieveAllTableAndInstance.CreateFile.Dao.md;
//import SqlInit.AchieveAllTableAndInstance.CreateFile.DaoSql.md_sql;
//
//import java.util.HashMap;
//import java.util.Map;
///**
// * 〈一句话功能简述〉<br>
// * 〈〉
// *
// * @author Administrator
// * @create 2020/02/19
// * @since 1.0.0
// */
//@Service("exampleimpl")
//public class exampleimpl implements example {
//
//    @Autowrite("md_sql")
//    public md_sql ms;
//
//    @Before("MyService.AopMethods.After.AfterAop")
//    @Override
//    public Map<String, md> show(@Paragrame("name") String name) {
//        System.out.println(name);
//        Map<String,md> map = null;
//        try {
//            map =  ms.SelectForname(name);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return map;
//    }
//}
