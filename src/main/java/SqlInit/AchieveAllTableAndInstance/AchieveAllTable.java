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
            ResultSet tables = metaData.getTables(null, null, null,new String[] { "TABLE" });
            while (tables.next()){
                Table table = new Table();
                table.setTablename(tables.getString("TABLE_NAME"));
                tablemap.put(table.getTablename(),table);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tablemap;
    }
    public Map<String,Table> getTableField(Map<String,Table> tablemap) throws SQLException {
        for (Map.Entry<String,Table> map:tablemap.entrySet()){
            String sql = "select * from "+map.getKey();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData meta = rs.getMetaData();
            int columeCount = meta.getColumnCount();
            Map<String,Integer> fieldmap = new HashMap<>();
            for (int i = 1; i < columeCount + 1; i++) {
                fieldmap.put(meta.getColumnName(i),meta.getColumnType(i));
            }
            Table table = map.getValue();
            table.setMap(fieldmap);
            map.setValue(table);
        }
        return tablemap;
    }
}
