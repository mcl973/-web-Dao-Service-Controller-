# -web-Dao-Service-Controller-
# 嗯，这是一个比较简陋的框架。
        应该是第四版了吧。
        实现了jsp页面渲染，主要是通过ApplicationDispattcher的forward（）方法来实现页面的跳转，其数据的传递是通过request的setAttribute和getAttribute来设置和获取数据。
        目前只支持jsp页面的渲染。
        改了些bug。
        第五版，修改了Annotation的一些检查，使得检查的更加彻底和更加的适用于本框架中的代码
        第六版，修改了MVC_handle中的一些代码，使其可以支持在WEB-INF文件夹下访问网页
        第七版，在DaoSql文件中添加读写锁，使得读写更加安全。
# 特点：
    1.这个框架可以把数据库中的表生成对应的java文件和简单的增删改查java文件，其中增删改查可以多参数查询和多参数输出，也可以批量增删改。
    2.实现了aop
    3.实现了controller，既可以动态的渲染网页数据
 
# 需要的资源：
    tomcat7，servlet、mysql-connect-java
# 在src/main的文件夹下
    前八个是在java文件夹下，第九个在webapp文件夹下。
    一、AllConfigure是一个配置类，包含了网页路径、java连接mysql数据库的配置
    二、Annotation_Collection是一个注解包，里面包含了注解：Dao、DaoSql、Service、Controller、RouteMapping...
      支持自定义注解，请将其放置在Annotation_Collection/MySelf/   文件夹下
    三、AutoJDK是一个jdk的动态代理，目前还不支持CGLB方式。aop的具体实现就是在这里，具体的是在MyInvokeHandler中。aop所需要使用的前置后置等等方法都是在运行时创建的。同时参数的自动注入也是在这里进行的。具体的可以进入这个文件看一下。
    四、MVC_Handle是一个通过url找到对应的Controller中的Method，执行完Method后返回一个Model，通过Model可以找到对应的网页，并且model中包含了Method执行返回的值。找到具体的网页路径后就可以将model中的值注入到网页中，然后再将网页返回到浏览器。可以实现动态渲染。
    urlkey--（通过iocmap得到）-->Method--->返回一个Model
                             |得到model
                             |
                             ----->获取model中的html url，并将model中的value和url传递给View
                             |
                             |
                             ----->view通过url找到具体的html，通过ApplicationDispatch的forward和request来实现页面跳转和数据传输。
    五、 MyController使用户编写Controller的地方。
    六、MyService使用户编写service的地方。其中MyService/AopMethod是提供aop方法，其中提供了几个例子，分别是after、before、around。用户可以自实现自  己的aop方法，但是必须要实现MyService/AopMethod/下的BaseInterface方法。MyService/impl是service的接口实现了，MyService/Interface 是声明接口的地方。
    七、ScannerAndInstance是整个框架的核心，它包含了扫描、实例化、aop处理、自动注入、路径映射。
     ScannerAndInstance/Scanner是一个扫描包，用户可以自己实现scanner，但是需要继承抽象类AbstractScannerBean。
     ScannerAndInstance/Instance是一个实例化包，用户可以实现自己的实例化方法，但是需要继承AbstraInstanceBean，其中ScannerAndInstance/Instance/GetJieXi是一个用于处理注解的类。
     ScannerAndInstance/HandAopEvent是一个处理aop的包，用户可以自实现，但是需要继承AbstractHandleAopBean。
     ScannerAndInstance/HandleAutowriteEvent是一个处理自动注入的包，用户可以自实现，但是需要继承AbstractHandleAutowrite。
      ScannerAndInstance/HandleRouteMappEvent是一个处理Controller层路径映射，用户可以自实现，但是需要继承AbstractHandleRoutemapping。
    八、SqlInit，是用来初始化数据库的。
      其中自动生成的Dao、DapSql在SqlInit/AchieveAllTableAndInstance/CreateFile/Dao、SqlInit/AchieveAllTableAndInstance/CreateFile/DaoSql
      自动生成代码可以这样：
        MySqlParagrame mySqlParagrame = new MySqlParagrame();
        new MySqlInit(mySqlParagrame.getSqlparam());
        AchieveAllTable achieveAllTable = new AchieveAllTable();
        Map<String, Table> tableField = achieveAllTable.getTableField(achieveAllTable.getTable());
        CreateMySqlNewFile createMySqlNewFile = new CreateMySqlNewFile();
        for(Map.Entry<String,Table> map:tableField.entrySet()){
            createMySqlNewFile.instance(map.getValue().getTablename(),map.getValue().getMap());
        }
      这样就可以创建Dao和DaoSql了。
    九、关于tomcat的配置
      由于我是使用idea的社区版配置的tomcat，所以我会按照我的来，对于专业版的idea配置tomcat就跟简单了。
      第一步  点击Run/Debug Configuration
      第二步  点击左上角的加号，选择maven
      第三步  创建好后，点击创建好的maven，将右边的name改为tomcat7，command line 改为tomcat7:run。
      第四步  确定
      第五步  在pom.xml文件中的添加以下配置，idea会自动下载tomcat：
              <plugin>
              <groupId>org.apache.tomcat.maven</groupId>
              <artifactId>tomcat7-maven-plugin</artifactId>
              <version>2.1</version>
              <configuration>
              <port>8080</port>
              <path>/</path>
              <uriEncoding>UTF-8</uriEncoding>
              <server>tomcat7</server>
              </configuration>
              </plugin>
      第六步  在pom.xml文件中添加一下配置，idea会自动下载：
              <dependency>
              <groupId>javax.servlet</groupId>
              <artifactId>servlet-api</artifactId>
              <version>2.5</version>
              <scope>provided</scope>
              </dependency>
              <dependency>
              <groupId>javax.servlet.jsp</groupId>
              <artifactId>jsp-api</artifactId>
              <version>2.1</version>
              <scope>provided</scope>
              </dependency>
              <!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
              <dependency>
              <groupId>mysql</groupId>
              <artifactId>mysql-connector-java</artifactId>
              <version>5.1.26</version>
              </dependency>
      第七步  在web.xml中添加以下代码：
              <servlet>
              <servlet-name>Dispatch</servlet-name>
              <servlet-class>ScannerAndInstance.Dispatch</servlet-class>
              <load-on-startup>0</load-on-startup>
              </servlet>
              <servlet-mapping>
              <servlet-name>Dispatch</servlet-name>
              <url-pattern>/</url-pattern>
              </servlet-mapping> 
      第八步  具体的操作步骤：
             1.在src/main/java/下运行test.java，这里可以创建属于你自己的Dao层文件。
                （1）在src/main/java/AllConfigure/Sql下的MySqlParagrame.java是用来配置数据库的信息的。
                        /*
                            Mysql的连接参数:
                                1. 驱动名称
                                2. 连接数据库的url
                                3. 用户名
                                4. 密码
                         */
                            String[] sqlparam = {"com.mysql.jdbc.Driver",
                                                    "jdbc:mysql://localhost:3306/mao",
                                                    "root",
                                                    "root"};
                （2）运行test，如果你想定义自己的运行文件也可以，请在你的文件的main函数里填入以下代码：
                        public static void main(String[] args) throws Exception {
                                MySqlParagrame mySqlParagrame = new MySqlParagrame();
                                new MySqlInit(mySqlParagrame.getSqlparam());
                                    AchieveAllTable achieveAllTable = new AchieveAllTable();
                                    Map<String, Table> tableField = achieveAllTable.getTableField(achieveAllTable.getTable());
                                    //开始构造dao、daosql文件
                                    CreateMySqlNewFile createMySqlNewFile = new CreateMySqlNewFile();
                                    //遍历Table容器
                                    for(Map.Entry<String,Table> map:tableField.entrySet()){
                                        //为每一张表建立dao、daosql文件
                                        createMySqlNewFile.instance(map.getValue().getTablename(),map.getValue().getMap());
                            }
                 （3）此时就可以在src/main/java/SqlInit/AchieveAllTableAndInstance/CreateFile下可以看见两个文件夹了Dao、DaoSql.
                           样例：
                               Dao：
                                 package SqlInit.AchieveAllTableAndInstance.CreateFile.Dao;

                                import Annotation_Collection.Sql.Dao;

                                @Dao("community")
                                public class community{public long communitytohome;
                                public long getcommunitytohome(){
                                return communitytohome;}
                                public void setcommunitytohome(long args){this.communitytohome=args;}
                                public long counts;
                                public long getcounts(){
                                return counts;}
                                public void setcounts(long args){this.counts=args;}
                                public String communityintroduce;
                                public String getcommunityintroduce(){
                                return communityintroduce;}
                                public void setcommunityintroduce(String args){this.communityintroduce=args;}
                                public String communityname;
                                public String getcommunityname(){
                                return communityname;}
                                public void setcommunityname(String args){this.communityname=args;}
                                public long communityid;
                                public long getcommunityid(){
                                return communityid;}
                                public void setcommunityid(long args){this.communityid=args;}
                                }
                            DaoSql：
                                package SqlInit.AchieveAllTableAndInstance.CreateFile.DaoSql;

                                import Annotation_Collection.Sql.DaoSql;
                                import SqlInit.AbstractSql;
                                import SqlInit.AchieveAllTableAndInstance.CreateFile.Dao.community;
                                import SqlInit.AchieveAllTableAndInstance.CreateFile.TypeTrans;

                                import java.lang.reflect.Field;
                                import java.sql.ResultSet;
                                import java.util.HashMap;
                                import java.util.Map;

                                /*本程序框架代码由自动生成*/
                                @DaoSql("community_sql")public class community_sql extends AbstractSql {
                                /*单个查询，输出匹配的所有的值，只能单个匹配
                                 参数为需要匹配的值，只传入值*/
                                public Map<String, community> SelectForcommunitytohome(long methodfield_) throws Exception{
                                  Map<String, community> classmap = new HashMap<>();
                                  ResultSet resultSet;
                                readLock.lock();
                                 resultSet = statement.executeQuery("select * from community where communitytohome=\"" +methodfield_+"\";");
                                 readLock.unlock();
                                  int m = 0;
                                  while(resultSet.next()){
                                community cn = new community();
                                String var0 = resultSet.getString("communitytohome");
                                cn.setcommunitytohome((long) TypeTrans.getvalue("long",var0));
                                String var1 = resultSet.getString("counts");
                                cn.setcounts((long) TypeTrans.getvalue("long",var1));
                                String var2 = resultSet.getString("communityintroduce");
                                cn.setcommunityintroduce((String) TypeTrans.getvalue("String",var2));
                                String var3 = resultSet.getString("communityname");
                                cn.setcommunityname((String) TypeTrans.getvalue("String",var3));
                                String var4 = resultSet.getString("communityid");
                                cn.setcommunityid((long) TypeTrans.getvalue("long",var4));
                                classmap.put(m+"",cn);
                                m++;
                                }return classmap;
                                }
                                /*单个查询，输出匹配的所有的值，只能单个匹配
                                 参数为需要匹配的值，只传入值*/
                                public Map<String, community> SelectForcounts(long methodfield_) throws Exception{
                                  Map<String, community> classmap = new HashMap<>();
                                  ResultSet resultSet;
                                readLock.lock();
                                 resultSet = statement.executeQuery("select * from community where counts=\"" +methodfield_+"\";");
                                 readLock.unlock();
                                  int m = 0;
                                  while(resultSet.next()){
                                community cn = new community();
                                String var0 = resultSet.getString("communitytohome");
                                cn.setcommunitytohome((long) TypeTrans.getvalue("long",var0));
                                String var1 = resultSet.getString("counts");
                                cn.setcounts((long) TypeTrans.getvalue("long",var1));
                                String var2 = resultSet.getString("communityintroduce");
                                cn.setcommunityintroduce((String) TypeTrans.getvalue("String",var2));
                                String var3 = resultSet.getString("communityname");
                                cn.setcommunityname((String) TypeTrans.getvalue("String",var3));
                                String var4 = resultSet.getString("communityid");
                                cn.setcommunityid((long) TypeTrans.getvalue("long",var4));
                                classmap.put(m+"",cn);
                                m++;
                                }return classmap;
                                }
                                /*单个查询，输出匹配的所有的值，只能单个匹配
                                 参数为需要匹配的值，只传入值*/
                                public Map<String, community> SelectForcommunityintroduce(String methodfield_) throws Exception{
                                  Map<String, community> classmap = new HashMap<>();
                                  ResultSet resultSet;
                                readLock.lock();
                                 resultSet = statement.executeQuery("select * from community where communityintroduce=\"" +methodfield_+"\";");
                                 readLock.unlock();
                                  int m = 0;
                                  while(resultSet.next()){
                                community cn = new community();
                                String var0 = resultSet.getString("communitytohome");
                                cn.setcommunitytohome((long) TypeTrans.getvalue("long",var0));
                                String var1 = resultSet.getString("counts");
                                cn.setcounts((long) TypeTrans.getvalue("long",var1));
                                String var2 = resultSet.getString("communityintroduce");
                                cn.setcommunityintroduce((String) TypeTrans.getvalue("String",var2));
                                String var3 = resultSet.getString("communityname");
                                cn.setcommunityname((String) TypeTrans.getvalue("String",var3));
                                String var4 = resultSet.getString("communityid");
                                cn.setcommunityid((long) TypeTrans.getvalue("long",var4));
                                classmap.put(m+"",cn);
                                m++;
                                }return classmap;
                                }
                                /*单个查询，输出匹配的所有的值，只能单个匹配
                                 参数为需要匹配的值，只传入值*/
                                public Map<String, community> SelectForcommunityname(String methodfield_) throws Exception{
                                  Map<String, community> classmap = new HashMap<>();
                                  ResultSet resultSet;
                                readLock.lock();
                                 resultSet = statement.executeQuery("select * from community where communityname=\"" +methodfield_+"\";");
                                 readLock.unlock();
                                  int m = 0;
                                  while(resultSet.next()){
                                community cn = new community();
                                String var0 = resultSet.getString("communitytohome");
                                cn.setcommunitytohome((long) TypeTrans.getvalue("long",var0));
                                String var1 = resultSet.getString("counts");
                                cn.setcounts((long) TypeTrans.getvalue("long",var1));
                                String var2 = resultSet.getString("communityintroduce");
                                cn.setcommunityintroduce((String) TypeTrans.getvalue("String",var2));
                                String var3 = resultSet.getString("communityname");
                                cn.setcommunityname((String) TypeTrans.getvalue("String",var3));
                                String var4 = resultSet.getString("communityid");
                                cn.setcommunityid((long) TypeTrans.getvalue("long",var4));
                                classmap.put(m+"",cn);
                                m++;
                                }return classmap;
                                }
                                /*单个查询，输出匹配的所有的值，只能单个匹配
                                 参数为需要匹配的值，只传入值*/
                                public Map<String, community> SelectForcommunityid(long methodfield_) throws Exception{
                                  Map<String, community> classmap = new HashMap<>();
                                  ResultSet resultSet;
                                readLock.lock();
                                 resultSet = statement.executeQuery("select * from community where communityid=\"" +methodfield_+"\";");
                                 readLock.unlock();
                                  int m = 0;
                                  while(resultSet.next()){
                                community cn = new community();
                                String var0 = resultSet.getString("communitytohome");
                                cn.setcommunitytohome((long) TypeTrans.getvalue("long",var0));
                                String var1 = resultSet.getString("counts");
                                cn.setcounts((long) TypeTrans.getvalue("long",var1));
                                String var2 = resultSet.getString("communityintroduce");
                                cn.setcommunityintroduce((String) TypeTrans.getvalue("String",var2));
                                String var3 = resultSet.getString("communityname");
                                cn.setcommunityname((String) TypeTrans.getvalue("String",var3));
                                String var4 = resultSet.getString("communityid");
                                cn.setcommunityid((long) TypeTrans.getvalue("long",var4));
                                classmap.put(m+"",cn);
                                m++;
                                }return classmap;
                                }
                                 /*单个多参数查询，包含两个参数均为map
                                 第二个数组为select和from之间的内容,为列名
                                 第一个为map是where后面的内容，key为列名，value为匹配的值*/
                                public Map<String, community> SelectForMore(Map<String, Object> selectfields, String[] outfield) throws Exception{
                                  Map<String, community> classmap = new HashMap<>();
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
                                } tempk++;}}  ResultSet resultSet;
                                readLock.lock();
                                 resultSet = statement.executeQuery("select "+out+" from community where "+select+";");
                                 readLock.unlock();
                                  int m = 0;
                                  while(resultSet.next()){
                                community cn = new community();
                                if(outfield == null){

                                Field[] fields = cn.getClass().getFields(); 
                                String result = "";
                                for (Field field : fields) { 
                                field.setAccessible(true);
                                field.set(cn, TypeTrans.getvalue(field.getType().getName(),getoneselectrecord(field.getName(),resultSet)));
                                }
                                }else{
                                for(String s1 : outfield) {
                                Field field = cn.getClass().getField(s1);
                                field.set(cn, TypeTrans.getvalue(field.getType().getName(),getoneselectrecord(s1,resultSet)));
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
                                String sql = "update community  set "+sets+" where "+wheres+";";

                                 writeLock.lock();
                                 boolean istrue = !statement.execute(sql);
                                 writeLock.unlock();
                                return istrue;
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
                                 String sql = "delete from community where "+deletes+";";
                                 writeLock.lock();
                                 boolean istrue = !statement.execute(sql);
                                 writeLock.unlock();
                                return istrue;
                                }
                                /*这个是单个处理插入的程序,需要传入的参数是一个关于这个跟表的对象*/
                                public boolean insertmethod(community object)throws Exception{

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
                                 String sql = "insert into community("+insertbefore+") values("+insertafter+");";
                                writeLock.lock();
                                 boolean istrue = !statement.execute(sql);
                                 writeLock.unlock();
                                return istrue; 
                                }
                                /*批量处理程序，可以处理大批量的sql语句，这里没有写死只需要将包含有sql语句的字符串数组传进来就可以了 */
                                 public boolean excuteBatch(String[] sqls) {
                                 boolean autoCommit = false;

                                 try {
                                 autoCommit = connection.getAutoCommit();
                                 int k = 0;
                                writeLock.lock();
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
                                 writeLock.unlock(); 
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
                                
     2.编写自己的Controller
         对于Models有一个需要提及的是在models中放置的是Object[],而不是object。
        在网页端从request中拿取的数据是Vector类型的。
        
        请在src\main\java\MyController下简历你自己的Controller
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
        @Controller("MyFirstControllerTest")  //controller的注释
        @RouteMapping("/controller")        //类上面的路由映射
        public class MyFirstControllerTest {

            @Autowrite("exampleimpl")    //自动注入
            public example example;
            /*
                针对于Springmvc应该是使用了HttpServletResponse的重定向
                然后使用HttpServletResponse的信息填充将数据填充进去
             */
            @RouteMapping("/test")  //函数上面的路由映射
            public Models   test(HttpServletResponse httpServletResponse,
                              HttpServletRequest httpServletRequest,
                              @Paragrame("name") String name){    //@Paragrame  参数上面的映射
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
       
           3.编写自己的service：在\src\main\java\MyService下
                编写接口
                package MyService.Interface;
                import SqlInit.AchieveAllTableAndInstance.CreateFile.Dao.md;
                import SqlInit.AchieveAllTableAndInstance.CreateFile.Dao.mycontext;
                import SqlInit.AchieveAllTableAndInstance.CreateFile.Dao.user;
                import SqlInit.AchieveAllTableAndInstance.CreateFile.Dao.users;

                import java.util.Map;

                public interface example {
                    public Map<String, md> show(String name);
                    public Map<String, users> login(String name);
                    public Map<String, mycontext> getAllContext(String name);
                }

               编写实现类
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
                import SqlInit.AchieveAllTableAndInstance.CreateFile.Dao.user;
                import SqlInit.AchieveAllTableAndInstance.CreateFile.Dao.users;
                import SqlInit.AchieveAllTableAndInstance.CreateFile.DaoSql.md_sql;
                import SqlInit.AchieveAllTableAndInstance.CreateFile.DaoSql.mycontext_sql;
                import SqlInit.AchieveAllTableAndInstance.CreateFile.DaoSql.user_sql;
                import SqlInit.AchieveAllTableAndInstance.CreateFile.DaoSql.users_sql;

                import java.util.HashMap;
                import java.util.Map;
                /**
                 * 〈一句话功能简述〉<br>
                 * 〈〉
                 *
                 * @author Administrator
                 * @create 2020/02/19
                 * @since 1.0.0
                 */
                @Service("exampleimpl")   //service层的注释
                public class exampleimpl implements example {

                    @Autowrite("md_sql")  //自动注入
                    public md_sql ms;

                    @Autowrite("users_sql")
                    public users_sql us;

                    @Autowrite("mycontext_sql")
                    public mycontext_sql mycontext_sql;

                    @Override
                    @After("MyService.AopMethods.After.AfterAop")  //处理aop事件的注释，里面的数据填写的函数的全限定名
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


                }
           4.自定义aop函数设计，在\src\main\java\MyService\AopMethods下：
                用户可以自实现自己的aop函数，但是必须要实现BaseInterface.java接口。
                如：
                public class AfterAop implements BaseInterface {

            @Override
            public Object Excute(Method method, Object object, Object[] objects) {
                try {
                    Object invoke = method.invoke(object, objects);
                    after();
                    return invoke;
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
                return null;
            }
            private void after(){
                System.out.println("thisis after test...");
            }
        }
    5.用户可以自实现自己的Annotation，但是这个Annotation只能是在方法上面，即只能为AopAnnotation。在src\main\java\Annotation_Collection\MySelf下：
        举例：
             package Annotation_Collection.MySelf;

                import java.lang.annotation.ElementType;
                import java.lang.annotation.Retention;
                import java.lang.annotation.RetentionPolicy;
                import java.lang.annotation.Target;

                @Target(ElementType.METHOD)
                @Retention(RetentionPolicy.RUNTIME)
                public @interface myseltest {
                    String value() default "";
                }
     6.配置网页url的地方是E:\java\First_Web_Frame_Test\src\main\java\AllConfigure\HtmlBasePath下的HtmlBasePath.java：
        public interface HtmlBasePath {
            //配置url的前缀，废弃
        //    src/main/webapp/WEB-INF/JSP/DataShow.jsp
            String BaseUrlPath = "E:\\java\\First_Web_Frame_Test\\src\\main\\webapp\\WEB-INF\\JSP\\";
            //配置url的后缀名
            String HouZhuiMing = ".jsp";
            String HttpPath = "/WEB-INF/JSP/"; //配置url的前缀，同时也是jsp文件放置的地方
        }
      7.开始运行程序吧
          样例1：
                https://blog.csdn.net/qq_30761967/article/details/104432788
          样例2：
                https://blog.csdn.net/qq_30761967/article/details/104523956
   
        
        

   
