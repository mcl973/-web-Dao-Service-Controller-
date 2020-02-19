/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: MySqlInit
 * Author:   Administrator
 * Date:     2020/02/16 10:28
 * Description: sql的初始话
 * History:
 * <author>          <time>          <version>          <desc>
 */
package SqlInit.SqlFind;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 〈一句话功能简述〉<br> 
 * 〈sql的初始话〉
 *
 * @author Administrator
 * @create 2020/02/16
 * @since 1.0.0
 */
public class MySqlInit extends MysqlFind {
    public MySqlInit(String[] args){
        super(args);
    }

    @Override
    public void getConnect() {
        if (statement == null && connection == null){
            synchronized (this){
                if (statement == null && connection == null){
                    try {
                        Class clazz = Class.forName(sqlinitmap.get(SqlInitNeedCommand.jdbc_driver));
                        connection = DriverManager.getConnection(sqlinitmap.get(SqlInitNeedCommand.jdbc_url),
                                sqlinitmap.get(SqlInitNeedCommand.jdbc_username),
                                sqlinitmap.get(SqlInitNeedCommand.jdbc_password));
                        statement=  connection.createStatement();
                    } catch (ClassNotFoundException | SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
