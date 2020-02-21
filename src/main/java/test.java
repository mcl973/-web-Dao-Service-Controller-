/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: test
 * Author:   Administrator
 * Date:     2020/02/16 11:27
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */

import Annotation_Collection.RouteMap.RouteMapping;
import Annotation_Collection.Sql.Dao;
import ScannerAndInstance.Dispatch;
import ScannerAndInstance.HandleAutowriteEvent.HandleAutowrite;
import ScannerAndInstance.HandleRouteMappEvent.HandleRouteMapping;
import ScannerAndInstance.HanleAopEvent.HandleAopBean;
import ScannerAndInstance.Instance.InstanceBean;
import ScannerAndInstance.Scanner.Scanner;
import SqlInit.AchieveAllTableAndInstance.AchieveAllTable;
import SqlInit.AchieveAllTableAndInstance.CreateFile.CreateMySqlNewFile;
import SqlInit.AchieveAllTableAndInstance.CreateFile.Dao.md;
import SqlInit.AchieveAllTableAndInstance.Table;
import SqlInit.SqlFind.MySqlInit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.Buffer;
import java.util.Map;
import java.util.Vector;

//import SqlInit.AchieveAllTableAndInstance.CreateFile.Dao.community;
//import SqlInit.AchieveAllTableAndInstance.CreateFile.Dao.md;
//import SqlInit.AchieveAllTableAndInstance.CreateFile.DaoSql.md_sql;
//import SqlInit.AchieveAllTableAndInstance.CreateFile.Dao.sw;
//import SqlInit.AchieveAllTableAndInstance.CreateFile.DaoSql.md_sql;
//import SqlInit.AchieveAllTableAndInstance.CreateFile.DaoSql.md_sql;
//import SqlInit.AchieveAllTableAndInstance.CreateFile.DaoSql.md_sql;
//import SqlInit.AchieveAllTableAndInstance.CreateFile.DaoSql.md_sql;
//import SqlInit.AchieveAllTableAndInstance.CreateFile.DaoSql.md_sql;
//import SqlInit.AchieveAllTableAndInstance.CreateFile.DaoSql.md_sql;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2020/02/16
 * @since 1.0.0
 */
public class test  {
//    public static void show(Map<String, md> stringmdMap ){
//        for(Map.Entry<String,md> map:stringmdMap.entrySet()){
//            System.out.println(map.getKey());
//            md value = map.getValue();
//            System.out.println(value.getage()+","+value.getid()+","+value.getname());
//        }
//    }
    public static  String getoneselectrecord(){
        String result ="";
        result+="public String getoneselectrecord(String name,String k){\n";
        result+= "String record = \"String \"+k+\" = resultSet.getString(\\\"\"+name+\"\\\");\";\n";
        result+="return record;}";
        return result;
    }
    public static void main(String[] args) throws Exception {
        String[] sqlparam = {"com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/mao",
        "root","root"};
        new MySqlInit(sqlparam);
            AchieveAllTable achieveAllTable = new AchieveAllTable();
            Map<String, Table> tableField = achieveAllTable.getTableField(achieveAllTable.getTable());
            CreateMySqlNewFile createMySqlNewFile = new CreateMySqlNewFile();
            for(Map.Entry<String,Table> map:tableField.entrySet()){
                createMySqlNewFile.instance(map.getValue().getTablename(),map.getValue().getMap());
            }


//        test1.testProxyGenetate();

//
//        Map<String,Object>  map= new HashMap<>();
//        map.put("name","abcdefghijkmmn");
//
//        Map<String,Object>  map2= new HashMap<>();
//        map2.put("id",3);
//        map2.put("age",24);
//        md md = new md();
//        md.setage(30);
//        md.setid(101);
//        md.setname("wasd");
//        System.out.println(new md_sql().insertmethod(md));
//        ResultSet resultSet = statement.executeQuery("select * from md");
//        while(resultSet.next()){
//            String id = resultSet.getString("id");
//            String name = resultSet.getString("name");
//            String age = resultSet.getString("age");
//            System.out.println(id+""+name+""+age);
//        }
        //下面开始创建文件
//        test1 test1 = new test1();
//        Class clazz = test1.getClass();
//        Field[] fields = clazz.getFields();
//        for (Field field : fields) {
//            System.out.println(field.getName()+"  "+field.getType());
//        }
//        System.out.println(test.class.getPackage().getName());
//        URL resource = test.class.getResource("");
//        String file = resource.getFile();
//        File f = new File(file);
//        String absolutePath = f.getAbsolutePath();
//        System.out.println(absolutePath);
//        MyClassLoader myClassLoader = new MyClassLoader("E:\\java\\First_AOP_IOC_Frame\\target\\classes\\JDK_Automatic\\");
//        try {
//            Class.forName("JAutomatic.class",true,myClassLoader);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        Class<test1> test1Class = test1.class;
//        Field a = test1Class.getField("a");
//        System.out.println(a.getType().getName());
//        sw sw = new sw();
//        Field max_delay = sw.getClass().getField("max_delay");
//        max_delay.set(sw,(max_delay.getType())TypeTrans.getvalue(max_delay.getType().getName(),"kk")));
//        getoneselectrecord(s1,"var"+k);
//        test1 t = new test1();
//        Field a = t.getClass().getField("a");
//        System.out.println(a.get(t));
//        System.out.println(getSqlAnnotation());
//        test1 test1 = new test1();
//        Class<? extends test1> aClass = test1.getClass();
//        Annotation annotation = aClass.getAnnotation(Dao.class);
//        String valueMethodreturn = getValueMethodreturn(annotation, findvaluemethod(annotation.getClass()));
//        System.out.println(valueMethodreturn);
//        String s1 = "11111myself3333myself4444myself";
//        s1 = s1.replaceAll("myself", "_");
//        System.out.println(s1);
//        test1 test1 = new test1();
//        Annotation[] annotations = test1.getClass().getAnnotations();
//        for (Annotation annotation : annotations) {
//            Class<? extends Annotation> aClass = annotation.getClass();
//            System.out.println(aClass.getName());
//            System.out.println(aClass.isAnnotation());
//            System.out.println(aClass.isAnnotationPresent(Target.class));
//        }
//
//        Class<test1> test1Class = test1.class;
//        Annotation[] annotations = test1Class.getAnnotations();
//        if (test1Class.isAnnotationPresent(Dao.class))
//            System.out.println("///////////");
//        else if (test1Class.isAnnotationPresent(RouteMapping.class))
//            System.out.println("...........");
//        Object object = new md();
//        Class<?> aClass = object.getClass();
//        String name = getLastNam(aClass.getName().toString());
//        System.out.println(name);
//        Field[] declaredFields = aClass.getDeclaredFields();
//        for (Field declaredField : declaredFields) {
//            System.out.println(declaredField.getName());
//        }
        Object object = "hello";
        String name = "kkkkk"+object;
        System.out.println(name);
    }

    public static String getLastNam(String name){
        char[] chars = name.toCharArray();
        String result = "";
        Vector<String> vector = new Vector<>();
        for (char aChar : chars) {
            if (aChar == '.'){
                vector.add(result);
                result = "";
            }
            else {
                result+=aChar;
            }
        }
        if (vector.size()!=0)
            return vector.lastElement();
        else return null;
    }

    public static String handlerhtml(String html,Object object){
        char[] chars = html.toCharArray();
        int i = 0;
        String str = "";
        String result = "";
        for (char aChar : chars) {
            if (aChar=='$') {
                i = 1;
                continue;
            }
            if (i==1) {
                if (aChar == '{')
                    continue;
                else if (aChar == '}')
                    i= 0;
                else {
                    result+=aChar;
                }
                continue;
            }
            if (!result.equals("")) {
                str +="\"+"+result+"+\"";
                result="";
            }
            if (aChar == '"')
                str+="\\\"";
            else
                str+=aChar;

        }
        if (!str.equals(""))
            return str;
        return null;
    }

//    public static String getValueMethodreturn(Object o, Method method){
//        try {
//            return (String)method.invoke(o,null);
//        } catch (IllegalAccessException | InvocationTargetException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
    public static Method findvaluemethod(Class clazz){
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (method.getName().equals("value"))
                return method;
        }
        return null;
    }

    public static String  getSqlAnnotation(){
        String name = "test.SqlInit.AchieveAllTableAndInstance.CreateFile";
        String qianzhui = "";
        if (name.contains("SqlInit")){
            qianzhui = name.split("SqlInit")[0];
            if (qianzhui.equals(""))
                qianzhui = "Annotation_Collection.Sql";
            else
                qianzhui += "Annotation_Collection.Sql";
            return qianzhui;
        }
        else
            return qianzhui;

    }
}
