/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: MysqlFind
 * Author:   Administrator
 * Date:     2020/02/16 10:14
 * Description: mysql
 * History:
 * <author>          <time>          <version>          <desc>
 */
package SqlInit.SqlFind;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 〈一句话功能简述〉<br> 
 * 〈mysql〉
 *
 * @author Administrator
 * @create 2020/02/16
 * @since 1.0.0
 */
public abstract class MysqlFind extends AbstractSqlFind {

    public MysqlFind(String[] sqlargs){
        super(sqlargs);
    }

    @Override
    public void init(String[] sqlargs) {
        sqlinitmap.put(SqlInitNeedCommand.jdbc_driver,sqlargs[0]);
        sqlinitmap.put(SqlInitNeedCommand.jdbc_url,sqlargs[1]);
        sqlinitmap.put(SqlInitNeedCommand.jdbc_username,sqlargs[2]);
        sqlinitmap.put(SqlInitNeedCommand.jdbc_password,sqlargs[3]);
    }



}
