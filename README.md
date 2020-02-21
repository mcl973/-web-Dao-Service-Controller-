# -web-Dao-Service-Controller-
# 嗯，这是一个比较简陋的框架。
# 特点：
    1.这个框架可以把数据库中的表生成对应的java文件和简单的增删改查java文件，其中增删改查可以多参数查询和多参数输出，也可以批量增删改。
    2.实现了aop
    3.实现了controller，既可以动态的渲染网页数据
 
# 需要的资源：
    tomcat7，servlet、mysql-connect-java
# 在java的文件夹下
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
                             ----->view通过url找到具体的html文件，在通过重组的方式将value注入到html中
                                             然后将html文件发送到浏览器。
    五、 MyController使用户编写Controller的地方。
    六、MyService使用户编写service的地方。其中MyService/AopMethod是提供aop方法，其中提供了几个例子，分别是after、before、around。用户可以自实现自  己的aop方法，但是必须要实现MyService/AopMethod/下的BaseInterface方法。MyService/impl是service的接口实现了，MyService/Interface 是声明接口的地方。
    七、ScannerAndInstance是整个框架的核心，它包含了扫描、实例化、aop处理、自动注入、路径映射。
         ScannerAndInstance/Scanner是一个扫描包，用户可以自己实现scanner
