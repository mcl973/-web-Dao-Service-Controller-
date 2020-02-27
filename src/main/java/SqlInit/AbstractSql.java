/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: AbstractSql
 * Author:   Administrator
 * Date:     2020/02/16 10:54
 * Description: 抽象的所有的sql
 * History:
 * <author>          <time>          <version>          <desc>
 */
package SqlInit;

import java.sql.Connection;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 〈一句话功能简述〉<br> 
 * 〈抽象的所有的sql〉
 *
 * @author Administrator
 * @create 2020/02/16
 * @since 1.0.0
 */
public abstract  class AbstractSql {
    public static Map<String,String> sqlinitmap = new HashMap<>();
    public static Statement statement = null;
    public static Connection connection = null;
    final static private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    public final static ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
    public final static ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
}
