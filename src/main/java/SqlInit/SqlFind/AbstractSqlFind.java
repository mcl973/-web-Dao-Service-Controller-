/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: AbstractSqlFind
 * Author:   Administrator
 * Date:     2020/02/16 10:13
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */
package SqlInit.SqlFind;

import SqlInit.AbstractSql;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 〈一句话功能简述〉<br> 
 * 〈抽象的sql发现和初始化工作〉
 *
 * @author Administrator
 * @create 2020/02/16
 * @since 1.0.0
 */
public abstract class AbstractSqlFind extends AbstractSql {
    public AbstractSqlFind(String[] sqlargs) {
        //初始话
        this.init(sqlargs);
        //得到具体的数据库连接
        this.getConnect();
        //处理完后将数据清空
        sqlinitmap = null;
    }
    public abstract void getConnect();
    public abstract void init(String[] sqlargs);
}
