package ScannerAndInstance; /**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName:
 * Author:   Administrator
 * Date:     2020/02/18 11:02
 * Description: 抽象的bean
 * History:
 * <author>          <time>          <version>          <desc>
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 〈一句话功能简述〉<br> 
 * 〈抽象的bean〉
 *
 * @author Administrator
 * @create 2020/02/18
 * @since 1.0.0
 */
public abstract class AbstractBean {
    //ioc容器
    public static Map<String,Object> iocmap = new HashMap<>();

    //用来存放filename的地方
    public static List<String> filesname = new ArrayList<>();

    public static Object getiocmap_Value(String key){
        return iocmap.getOrDefault(key, null);
    }
}
