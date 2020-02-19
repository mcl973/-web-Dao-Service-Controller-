/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: Table
 * Author:   Administrator
 * Date:     2020/02/16 11:02
 * Description: 表
 * History:
 * <author>          <time>          <version>          <desc>
 */
package SqlInit.AchieveAllTableAndInstance;

import java.util.HashMap;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈表〉
 *
 * @author Administrator
 * @create 2020/02/16
 * @since 1.0.0
 */
public class Table {
    String tablename;
    Map<String,Integer> map = new HashMap<>();

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public Map<String, Integer> getMap() {
        return map;
    }

    public void setMap(Map<String, Integer> map) {
        this.map = map;
    }
}
