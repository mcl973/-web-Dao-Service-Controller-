package SqlInit.AchieveAllTableAndInstance.CreateFile.DaoSql;
import java.sql.Date;

 import SqlInit.AbstractSql;

 import java.sql.ResultSet;

 import java.util.Map;

 import java.util.HashMap;

 import java.util.HashMap;

 import java.lang.reflect.Field;

 import Annotation_Collection.Sql.*;

 import SqlInit.AchieveAllTableAndInstance.CreateFile.Dao.user;
import SqlInit.AchieveAllTableAndInstance.CreateFile.TypeTrans;

/*本程序框架代码由自动生成*/
@DaoSql("user_sql")public class user_sql extends AbstractSql{
/*单个查询，输出匹配的所有的值，只能单个匹配
 参数为需要匹配的值，只传入值*/
public Map<String,user> SelectForbirthday(Date methodfield_) throws Exception{
  Map<String,user> classmap = new HashMap<>();
  ResultSet resultSet = statement.executeQuery("select * from user where birthday=" +methodfield_+";");
  int m = 0;
  while(resultSet.next()){
user cn = new user();
String var0 = resultSet.getString("birthday");
cn.setbirthday((Date)TypeTrans.getvalue("Date",var0));
String var1 = resultSet.getString("address");
cn.setaddress((String)TypeTrans.getvalue("String",var1));
String var2 = resultSet.getString("sex");
cn.setsex((String)TypeTrans.getvalue("String",var2));
String var3 = resultSet.getString("id");
cn.setid((long)TypeTrans.getvalue("long",var3));
String var4 = resultSet.getString("username");
cn.setusername((String)TypeTrans.getvalue("String",var4));
classmap.put(m+"",cn);
m++;
}return classmap;
}
/*单个查询，输出匹配的所有的值，只能单个匹配
 参数为需要匹配的值，只传入值*/
public Map<String,user> SelectForaddress(String methodfield_) throws Exception{
  Map<String,user> classmap = new HashMap<>();
  ResultSet resultSet = statement.executeQuery("select * from user where address=" +methodfield_+";");
  int m = 0;
  while(resultSet.next()){
user cn = new user();
String var0 = resultSet.getString("birthday");
cn.setbirthday((Date)TypeTrans.getvalue("Date",var0));
String var1 = resultSet.getString("address");
cn.setaddress((String)TypeTrans.getvalue("String",var1));
String var2 = resultSet.getString("sex");
cn.setsex((String)TypeTrans.getvalue("String",var2));
String var3 = resultSet.getString("id");
cn.setid((long)TypeTrans.getvalue("long",var3));
String var4 = resultSet.getString("username");
cn.setusername((String)TypeTrans.getvalue("String",var4));
classmap.put(m+"",cn);
m++;
}return classmap;
}
/*单个查询，输出匹配的所有的值，只能单个匹配
 参数为需要匹配的值，只传入值*/
public Map<String,user> SelectForsex(String methodfield_) throws Exception{
  Map<String,user> classmap = new HashMap<>();
  ResultSet resultSet = statement.executeQuery("select * from user where sex=" +methodfield_+";");
  int m = 0;
  while(resultSet.next()){
user cn = new user();
String var0 = resultSet.getString("birthday");
cn.setbirthday((Date)TypeTrans.getvalue("Date",var0));
String var1 = resultSet.getString("address");
cn.setaddress((String)TypeTrans.getvalue("String",var1));
String var2 = resultSet.getString("sex");
cn.setsex((String)TypeTrans.getvalue("String",var2));
String var3 = resultSet.getString("id");
cn.setid((long)TypeTrans.getvalue("long",var3));
String var4 = resultSet.getString("username");
cn.setusername((String)TypeTrans.getvalue("String",var4));
classmap.put(m+"",cn);
m++;
}return classmap;
}
/*单个查询，输出匹配的所有的值，只能单个匹配
 参数为需要匹配的值，只传入值*/
public Map<String,user> SelectForid(long methodfield_) throws Exception{
  Map<String,user> classmap = new HashMap<>();
  ResultSet resultSet = statement.executeQuery("select * from user where id=" +methodfield_+";");
  int m = 0;
  while(resultSet.next()){
user cn = new user();
String var0 = resultSet.getString("birthday");
cn.setbirthday((Date)TypeTrans.getvalue("Date",var0));
String var1 = resultSet.getString("address");
cn.setaddress((String)TypeTrans.getvalue("String",var1));
String var2 = resultSet.getString("sex");
cn.setsex((String)TypeTrans.getvalue("String",var2));
String var3 = resultSet.getString("id");
cn.setid((long)TypeTrans.getvalue("long",var3));
String var4 = resultSet.getString("username");
cn.setusername((String)TypeTrans.getvalue("String",var4));
classmap.put(m+"",cn);
m++;
}return classmap;
}
/*单个查询，输出匹配的所有的值，只能单个匹配
 参数为需要匹配的值，只传入值*/
public Map<String,user> SelectForusername(String methodfield_) throws Exception{
  Map<String,user> classmap = new HashMap<>();
  ResultSet resultSet = statement.executeQuery("select * from user where username=" +methodfield_+";");
  int m = 0;
  while(resultSet.next()){
user cn = new user();
String var0 = resultSet.getString("birthday");
cn.setbirthday((Date)TypeTrans.getvalue("Date",var0));
String var1 = resultSet.getString("address");
cn.setaddress((String)TypeTrans.getvalue("String",var1));
String var2 = resultSet.getString("sex");
cn.setsex((String)TypeTrans.getvalue("String",var2));
String var3 = resultSet.getString("id");
cn.setid((long)TypeTrans.getvalue("long",var3));
String var4 = resultSet.getString("username");
cn.setusername((String)TypeTrans.getvalue("String",var4));
classmap.put(m+"",cn);
m++;
}return classmap;
}
 /*单个多参数查询，包含两个参数均为map
 第二个数组为select和from之间的内容,为列名
 第一个为map是where后面的内容，key为列名，value为匹配的值*/
public Map<String,user> SelectForMore(Map<String, Object> selectfields,String[] outfield) throws Exception{
  Map<String,user> classmap = new HashMap<>();
int tempk = 0;String select = "";for(Map.Entry<String,Object> map:selectfields.entrySet()){
 if (tempk == 0) {
select += map.getKey() +"="+ map.getValue();
}else{
select += " and  " + map.getKey()+"="+map.getValue();
}
tempk++;
};
String out = "";
if (outfield == null){
out="*";
}else{
tempk = 0;
for(String s : outfield){
if(tempk==0){
out+=s;
}else{
out+=","+s;
} tempk++;}}  ResultSet resultSet = statement.executeQuery("select "+out+" from user where "+select+";");
  int m = 0;
  while(resultSet.next()){
user cn = new user();
if(outfield == null){

Field[] fields = cn.getClass().getFields(); 
String result = "";
for (Field field : fields) { 
field.setAccessible(true);
field.set(cn,TypeTrans.getvalue(field.getType().getName(),getoneselectrecord(field.getName(),resultSet)));
}
}else{
for(String s1 : outfield) {
Field field = cn.getClass().getField(s1);
field.set(cn,TypeTrans.getvalue(field.getType().getName(),getoneselectrecord(s1,resultSet)));
}}
classmap.put(m+"",cn);
m++;
}return classmap;
}
public String getoneselectrecord(String name,ResultSet resultSet)throws Exception{
String var = resultSet.getString(name);
return var;
}
/*这个是一个单个更新的函数
 输入的参数是一对map
 第一个map包含的是set 后面的内容，即需要更新的内容key为列名，value为需要修改的值
第二个map包含的是where后面的匹配项,key为列名，value为需要匹配的值*/
public boolean UpdateForMore(Map<String,Object> mapupdata,Map<String,Object> mapwhere)throws Exception{
String sets = "";
 
 int k = 0;
 for(Map.Entry<String,Object> map:mapupdata.entrySet()){
 if (k==0)
 sets+=map.getKey()+"=\""+map.getValue()+"\"";
 else
 sets+=","+map.getKey()+"=\""+map.getValue()+"\"";
 }
 k = 0; 
 
 String wheres = "";
 for(Map.Entry<String,Object> map:mapwhere.entrySet()){ 
  if (k==0)
 wheres+=map.getKey()+"=\""+map.getValue()+"\"";
 else
 wheres+=" and "+map.getKey()+"=\""+map.getValue()+"\"";
 k++; 
 }
 k = 0;
String sql = "update user  set "+sets+" where "+wheres+";";
 
 if (!statement.execute(sql)) {
 return true;
   }
 else
 return false;
 
}
 /*这个是一个单个删除的函数，用于处理删除，
传进来的是一个包含有需要删除的具体项的map，
map里包含的是where里需要匹配的东西*/
public boolean deleteformore(Map<String,Object> mapdelete)throws Exception{
 
 int k = 0;
 String deletes = "";
 for(Map.Entry<String,Object> map:mapdelete.entrySet()){
 if (k == 0)
 deletes+=map.getKey()+"=\""+map.getValue()+"\"";
 else
deletes+=" , "+map.getKey()+"=\""+map.getValue()+"\"";
  }
 String sql = "delete from user where "+deletes+";";
 if (!statement.execute(sql)) {
 return true;
   }
 else
 return false;
 }
/*这个是单个处理插入的程序,需要传入的参数是一个关于这个跟表的对象*/
public boolean insertmethod(user object)throws Exception{
 
 String insertbefore = "";
 String insertafter = "";
  int k = 0;
 for (Field field : object.getClass().getFields()) {
 field.setAccessible(true);
 if(k == 0) {
   insertbefore += field.getName();
insertafter += "\""+field.get(object)+"\"";
 }else{
 insertbefore += ","+field.getName();
 insertafter += ","+"\""+field.get(object)+"\"";
 }
 k++;
   }
 String sql = "insert into user("+insertbefore+") values("+insertafter+");";
 if (!statement.execute(sql)) {
 return true;
   }
 else
 return false;
 }
/*批量处理程序，可以处理大批量的sql语句，这里没有写死只需要将包含有sql语句的字符串数组传进来就可以了 */
 public boolean excuteBatch(String[] sqls) {
 boolean autoCommit = false;
 
 try {
 autoCommit = connection.getAutoCommit();
 int k = 0;
 for (String sql : sqls) {
 if (k>=1000){
   k = 0;
   statement.executeBatch();
 connection.commit();
   }
  statement.addBatch(sql);
         }
    statement.executeBatch();
 connection.commit();
 connection.setAutoCommit(autoCommit);
 return true;
 } catch (Exception e) {
  try {
  connection.rollback();
 } catch (Exception e1) {
 e1.printStackTrace();
 } 
  }
 return false;
 }
 

}