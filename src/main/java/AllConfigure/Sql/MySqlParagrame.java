/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: MySqlParagrame
 * Author:   Administrator
 * Date:     2020/02/21 14:43
 * Description: mysql的配资
 * History:
 * <author>          <time>          <version>          <desc>
 */
package AllConfigure.Sql;

/**
 * 〈一句话功能简述〉<br> 
 * 〈mysql的配资〉
 *
 * @author Administrator
 * @create 2020/02/21
 * @since 1.0.0
 */
public class MySqlParagrame {
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

    public String[] getSqlparam() {
        return sqlparam;
    }

    public void setSqlparam(String[] sqlparam) {
        this.sqlparam = sqlparam;
    }
}
