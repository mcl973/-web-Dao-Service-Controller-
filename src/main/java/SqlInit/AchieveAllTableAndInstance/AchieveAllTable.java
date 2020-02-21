/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: AchieveAllTable
 * Author:   Administrator
 * Date:     2020/02/16 10:50
 * Description: 获取到数据库中所有的表
 * History:
 * <author>          <time>          <version>          <desc>
 */
package SqlInit.AchieveAllTableAndInstance;

import SqlInit.AbstractSql;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈获取到数据库中所有的表〉
 *
 * @author Administrator
 * @create 2020/02/16
 * @since 1.0.0
 */
public class AchieveAllTable extends AbstractSql implements TablesInterface{

    public Map<String,Table> getTable(){
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            //得到所有的table
            ResultSet tables = metaData.getTables(null, null, null,new String[] { "TABLE" });
            //遍历tables
            while (tables.next()){
                Table table = new Table();
                //将table的名字放入Table中
                table.setTablename(tables.getString("TABLE_NAME"));
                //将Table放入容器中
                tablemap.put(table.getTablename(),table);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tablemap;
    }
    public Map<String,Table> getTableField(Map<String,Table> tablemap) throws SQLException {
        //遍历容器
        for (Map.Entry<String,Table> map:tablemap.entrySet()){
            //获取sql，查询表中的所有的字段
            String sql = "select * from "+map.getKey();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            //获取属性
            ResultSetMetaData meta = rs.getMetaData();
            int columeCount = meta.getColumnCount();
            //属性容器
            Map<String,Integer> fieldmap = new HashMap<>();
            //遍历属性
            for (int i = 1; i < columeCount + 1; i++) {
                //获取属性名，属性类型
                fieldmap.put(meta.getColumnName(i),meta.getColumnType(i));
            }
            //将属性容器放入Table中
            Table table = map.getValue();
            table.setMap(fieldmap);
            //再将Table返回给容器
            map.setValue(table);
        }
        return tablemap;
    }
}
