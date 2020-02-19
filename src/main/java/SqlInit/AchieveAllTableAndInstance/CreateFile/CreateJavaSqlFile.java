/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: CreateJavaSqlFile
 * Author:   Administrator
 * Date:     2020/02/16 16:24
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */
package SqlInit.AchieveAllTableAndInstance.CreateFile;


import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2020/02/16
 * @since 1.0.0
 */
public class CreateJavaSqlFile extends AbstractGenerateFile implements CreateMethods{
    private Map<String, String> namemap;
    /*
        首先就是如何编写查询语句
        只支持增删改查
        不支持事务
     */
    @Override
    public String getjavaclass(String classname, Map<String, Integer> fieldmap, String dao) {
        String packages = "package "+getPackage()+"."+dao+";";
        List<Object> nameAndType = getNameAndType(fieldmap);
        String imports = (String) nameAndType.get(0);
        String sqlInit = getPackage().split("SqlInit")[0];
        if (!sqlInit.equals(""))
            imports+="\n import "+sqlInit+".SqlInit.AbstractSql;\n";
        else imports+="\n import SqlInit.AbstractSql;\n";
        imports+="\n import java.sql.ResultSet;\n";
        imports+="\n import java.util.Map;\n";
        imports+="\n import java.util.HashMap;\n";
        imports+="\n import java.util.HashMap;\n";
        imports+="\n import java.lang.reflect.Field;\n";
        imports+="\n import "+getSqlAnnotation()+".*;\n";
        imports+=getthisclassimport(classname);
        imports+=GetExistClassPackage(TypeTrans.class,"TypeTrans");
        List<Object> nameAndType1 = getNameAndType(fieldmap);
        namemap = (Map<String, String>)nameAndType1.get(1);
        String introduce = "/*本程序框架代码由自动生成*/\n";
        String sqlfile = packages +"\n"+imports+"\n"+introduce+"@DaoSql(\""+classname+"_sql\")"+"public class "+classname+"_sql extends AbstractSql{\n";
        //添加单个查询
        for(Map.Entry<String,Integer> map:fieldmap.entrySet()) {
            sqlfile += getSelectForId(classname, map.getKey(),namemap);
        }
        //添加多选项查询和多选项输出
        sqlfile+=getSelectForMore(classname);
        //添加查询单个语句的函数
        sqlfile+=GetOneSelectRecord();
        //添加更新函数
        sqlfile+=GetUpdateMethod(classname);
        //添加删除函数
        sqlfile+=GetDeleteMethod(classname);
        //添加 添加函数
        sqlfile+=GetInsertMethod(classname);
        //添加 批量处理程序
        sqlfile+=getBatchMethod();
        String endfile = "\n}";
        return sqlfile+endfile;
    }
    //构建查询的方法
    public String getSelectForId(String classname,String methodfield,Map<String, String> fieldmap){
        String introduce ="/*单个查询，输出匹配的所有的值，只能单个匹配\n 参数为需要匹配的值，只传入值*/\n";
        String MethodBody = introduce+"public Map<String,"+classname+"> SelectFor"+methodfield+"("+fieldmap.get(methodfield)+" methodfield_) throws Exception{\n";
        MethodBody+="  Map<String,"+classname+"> classmap = new HashMap<>();\n";
        String sql = "select * from "+classname+" where "+methodfield+"="+"\\\"\" +methodfield_+\"\\\";";
        MethodBody+="  ResultSet resultSet = statement.executeQuery" +
            "(\""+sql+"\");\n";
        MethodBody+="  int m = 0;\n";
        MethodBody+="  while(resultSet.next()){\n";
        int k = 0;
        MethodBody+= classname +" cn = new "+classname+"();\n";
        for(Map.Entry<String,String> map:fieldmap.entrySet()) {
            MethodBody += getoneselectrecord(map.getKey(),"var"+k);
            MethodBody += "cn.set"+map.getKey()+"(("+map.getValue ()+")TypeTrans.getvalue(\""+map.getValue ()+"\",var"+k+"));\n";
            k++;
        }
        MethodBody+="classmap.put(m+\"\",cn);\n";
        MethodBody+="m++;\n";
        MethodBody+="}";
        MethodBody+="return classmap;\n";
        MethodBody+="}\n" ;
        return MethodBody;
    }

    //classname 查询条件<查询名称，值>  输出条件 Map<String, String> fieldmap
    public String getSelectForMore(String classname){
        String introduce = " /*单个多参数查询，包含两个参数均为map\n 第二个数组为select和from之间的内容,为列名\n 第一个为map是where后面的内容，key为列名，value为匹配的值*/\n";
        String MethodBody = introduce+"public Map<String,"+classname+"> SelectForMore(Map<String, Object> selectfields,String[] outfield) throws Exception{\n";
        MethodBody+="  Map<String,"+classname+"> classmap = new HashMap<>();\n";
        String getselect = "";
        //获取select具体的sql条目
        getselect+="int tempk = 0;";
        getselect+="String select = \"\";";
        getselect+="for(Map.Entry<String,Object> map:selectfields.entrySet()){\n";
        getselect+=" if (tempk == 0) {\n";
        getselect+="select += map.getKey() +\"=\"+ map.getValue();\n";
        getselect+="}else{\n";
        getselect+="select += \" and  \" + map.getKey()+\"=\"+map.getValue();\n";
        getselect+="}\n";
        getselect+="tempk++;\n";
        getselect+="};\n";
        MethodBody+=getselect;
        //获取输出条件
        String getout = "";
        getout+="String out = \"\";\n";
        //判断outfield是否为空
        getout+="if (outfield == null){\nout=\"*\";\n}else{\n";
        getout+="tempk = 0;\nfor(String s : outfield){\nif(tempk==0){\nout+=s;\n}else{\nout+=\",\"+s;\n} tempk++;}}";
        MethodBody+=getout;
        //开始组装成一个sql
        String sql = "select \"+out+\" from "+classname+" where \"+select+\";";
        MethodBody+="  ResultSet resultSet = statement.executeQuery" +
        "(\""+sql+"\");\n";
        //开始获取输出的结果
        MethodBody+="  int m = 0;\n";
        MethodBody+="  while(resultSet.next()){\n";
        MethodBody+= classname +" cn = new "+classname+"();\n";
        //现在有值了，那就要通过这个值也就是列明区获取相应的类型
        MethodBody+="if(outfield == null){\n";
        MethodBody+= "\n" +
            "Field[] fields = cn.getClass().getFields(); \n" +
            "String result = \"\";\n" +
            "for (Field field : fields) { \n" +
             "field.setAccessible(true);\n"+
            "field.set(cn,TypeTrans.getvalue(field.getType().getName(),getoneselectrecord(field.getName(),resultSet)));\n"+
            "}\n";
        MethodBody+="}else{\nfor(String s1 : outfield) {\n";
        //            MethodBody += "String var+k = resultSet.getString(s1);\n";
        MethodBody += "Field field = cn.getClass().getField(s1);\n";
        MethodBody+="field.set(cn,TypeTrans.getvalue(field.getType().getName(),getoneselectrecord(s1,resultSet)));\n";
        MethodBody+="}}\n";
        MethodBody+="classmap.put(m+\"\",cn);\n";
        MethodBody+="m++;\n";
        MethodBody+="}";
        MethodBody+="return classmap;\n";
        MethodBody+="}\n" ;
        return MethodBody;
    }

    public String GetUpdateMethod(String classname){
        String introduce = "/*这个是一个单个更新的函数\n 输入的参数是一对map\n 第一个map包含的是set 后面的内容，即需要更新的内容key为列名，value为需要修改的值\n第二个map包含的是where后面的匹配项,key为列名，value为需要匹配的值*/\n";
        String updatemethod = introduce+"public boolean UpdateForMore(Map<String,Object> mapupdata,Map<String,Object> mapwhere)throws Exception{\n";
        updatemethod+="String sets = \"\";\n";
        updatemethod+=" \n";
        updatemethod+=" int k = 0;\n";
        updatemethod+=" for(Map.Entry<String,Object> map:mapupdata.entrySet()){\n";
        updatemethod+=" if (k==0)\n";
        updatemethod+=" sets+=map.getKey()+\"=\\\"\"+map.getValue()+\"\\\"\";\n";
        updatemethod+=" else\n";
        updatemethod+=" sets+=\",\"+map.getKey()+\"=\\\"\"+map.getValue()+\"\\\"\";\n";
        updatemethod+=" }\n";
        updatemethod+=" k = 0; \n";
        updatemethod+=" \n";
        updatemethod+=" String wheres = \"\";\n";
        updatemethod+=" for(Map.Entry<String,Object> map:mapwhere.entrySet()){ \n";
        updatemethod+="  if (k==0)\n";
        updatemethod+=" wheres+=map.getKey()+\"=\\\"\"+map.getValue()+\"\\\"\";\n";
        updatemethod+=" else\n";
        updatemethod+=" wheres+=\" and \"+map.getKey()+\"=\\\"\"+map.getValue()+\"\\\"\";\n";
        updatemethod+=" k++; \n";
        updatemethod+=" }\n";
        updatemethod+=" k = 0;\n";
        updatemethod+="String sql = \"update "+classname+"  set \"+sets+\" where \"+wheres+\";\";\n";
        updatemethod+=" \n";
        updatemethod+=" if (!statement.execute(sql)) {\n";
        updatemethod+=" return true;\n";
        updatemethod+="   }\n";
        updatemethod+=" else\n";
        updatemethod+=" return false;\n";
        updatemethod+=" \n";
        return updatemethod+"}\n";
    }
    public String GetDeleteMethod(String classname){
        String introduce = " /*这个是一个单个删除的函数，用于处理删除，\n传进来的是一个包含有需要删除的具体项的map，\nmap里包含的是where里需要匹配的东西*/\n";
        String deletemethod = introduce+"public boolean deleteformore(Map<String,Object> mapdelete)throws Exception{\n";
        Map<String,Object> mapdelete = new HashMap<>();
        deletemethod += " \n";
        deletemethod += " int k = 0;\n";
        deletemethod += " String deletes = \"\";\n";
        deletemethod += " for(Map.Entry<String,Object> map:mapdelete.entrySet()){\n";
        deletemethod += " if (k == 0)\n";
        deletemethod += " deletes+=map.getKey()+\"=\\\"\"+map.getValue()+\"\\\"\";\n";
        deletemethod += " else\n";
        deletemethod += "deletes+=\" , \"+map.getKey()+\"=\\\"\"+map.getValue()+\"\\\"\";\n";
        deletemethod += "  }\n";
        deletemethod += " String sql = \"delete from "+classname+" where \"+deletes+\";\";\n";
        deletemethod+=" if (!statement.execute(sql)) {\n";
        deletemethod+=" return true;\n";
        deletemethod+="   }\n";
        deletemethod+=" else\n";
        deletemethod+=" return false;\n";
        deletemethod+=" }\n";
        return deletemethod;
    }
    public String GetInsertMethod(String classname) {
        String introduce = "/*这个是单个处理插入的程序,需要传入的参数是一个关于这个跟表的对象*/\n";
        String insertmethod =introduce+ "public boolean insertmethod("+classname+" object)throws Exception{\n";
        insertmethod+=" \n";
        insertmethod+=" String insertbefore = \"\";\n";
        insertmethod+=" String insertafter = \"\";\n";
        insertmethod+="  int k = 0;\n";
        insertmethod+=" for (Field field : object.getClass().getFields()) {\n";
        insertmethod+=" field.setAccessible(true);\n";
        insertmethod+=" if(k == 0) {\n";
        insertmethod+="   insertbefore += field.getName();\n";
        insertmethod+="insertafter += \"\\\"\"+field.get(object)+\"\\\"\";\n";
        insertmethod+=" }else{\n";
        insertmethod+=" insertbefore += \",\"+field.getName();\n";
        insertmethod+=" insertafter += \",\"+\"\\\"\"+field.get(object)+\"\\\"\";\n";

        insertmethod+=" }\n";
        insertmethod+=" k++;\n";
        insertmethod+="   }\n";
        insertmethod+=" String sql = \"insert into "+classname+"(\"+insertbefore+\") values(\"+insertafter+\");\";\n";
        insertmethod+=" if (!statement.execute(sql)) {\n";
        insertmethod+=" return true;\n";
        insertmethod+="   }\n";
        insertmethod+=" else\n";
        insertmethod+=" return false;\n";
        insertmethod+=" }\n";
        return insertmethod;
    }

    public String getBatchMethod() {
        String batchmethod = "/*批量处理程序，可以处理大批量的sql语句，这里没有写死" +
                "只需要将包含有sql语句的字符串数组传进来就可以了 */\n";
        batchmethod += " public boolean excuteBatch(String[] sqls) {\n";
        batchmethod += " boolean autoCommit = false;\n";
        batchmethod += " \n";
        batchmethod += " try {\n";
        batchmethod += " autoCommit = connection.getAutoCommit();\n";
        batchmethod += " int k = 0;\n";
        batchmethod += " for (String sql : sqls) {\n";
        batchmethod += " if (k>=1000){\n";
        batchmethod += "   k = 0;\n";
        batchmethod += "   statement.executeBatch();\n";
        batchmethod += " connection.commit();\n";
        batchmethod += "   }\n";
        batchmethod += "  statement.addBatch(sql);\n";
        batchmethod += "         }\n";
        batchmethod += "    statement.executeBatch();\n";
        batchmethod += " connection.commit();\n";
        batchmethod += " connection.setAutoCommit(autoCommit);\n";
        batchmethod += " return true;\n";
        batchmethod += " } catch (Exception e) {\n";
        batchmethod += "  try {\n";
        batchmethod += "  connection.rollback();\n";
        batchmethod += " } catch (Exception e1) {\n";
        batchmethod += " e1.printStackTrace();\n";
        batchmethod += " } \n";
        batchmethod += "  }\n";
        batchmethod += " return false;\n";
        batchmethod += " }\n";
        batchmethod += " \n";
        return batchmethod;
    }
    public String GetOneSelectRecord(){
        String result ="";
        result+="public String getoneselectrecord(String name,ResultSet resultSet)throws Exception{\n";
        result+= "String var = resultSet.getString(name);\n";
        result+="return var;\n}\n";
        return result;
    }
    //获得一次查询记录
    private String getoneselectrecord(String name,String k){
        String record = "String "+k+" = resultSet.getString(\""+name+"\");\n";
        return record;
    }
    //获取这个sql类对应的Dao层的具体的类的import
    private String getthisclassimport(String classname){
        String name = getPackage();
        String classimport = "\n import "+name;
        classimport+=".Dao."+classname+";\n";
        return classimport;
    }
    private String GetExistClassPackage(Class clazz,String classname){
        Package aPackage = clazz.getPackage();
        String name = aPackage.getName();
        return "import "+ name+"."+classname+";\n";
    }

}
