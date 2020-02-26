/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: HtmlBasePath
 * Author:   Administrator
 * Date:     2020/02/21 10:00
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */
package AllConfigure.HtmlBasePath;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2020/02/21
 * @since 1.0.0
 */
public interface HtmlBasePath {
    //配置url的前缀
    String BaseUrlPath = "E:\\java\\First_Web_Frame_Test\\src\\main\\webapp\\JSP\\";
    //配置url的后缀名
    String HouZhuiMing = ".jsp";
    String HttpPath = "/JSP/"; //这里填写的是jsp文件放置的相对位置，在我的项目中在WebApp下面.
                                //切记不能放在WebApp/WEB-INF下面，不然会有问题，具体的问题就是解析不到jsp文件
                                //我想tomcat应该是做了手脚的，即定好了WEB-INF问配置文件的地方，html、jsp文件不能放在这里

}
