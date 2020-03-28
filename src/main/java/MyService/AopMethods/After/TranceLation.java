/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: TranceLation
 * Author:   Administrator
 * Date:     2020/03/17 22:24
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */
package MyService.AopMethods.After;

import MyService.AopMethods.BaseInterface;
import SqlInit.AbstractSql;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;

/**
 * 〈一句话功能简述〉<br> 
 * 〈事务操作〉
 *
 * @author Administrator
 * @create 2020/03/17
 * @since 1.0.0
 */
public class TranceLation implements BaseInterface {
    @Override
    public Object Excute(Method method, Object object, Object[] objects) {
        Object result = null;
        try {
            result = method.invoke(object, objects);
        } catch (IllegalAccessException | InvocationTargetException e) {
            RollBack();
        }
        return result;
    }
    public void RollBack(){
        try {
            AbstractSql.connection.rollback();
        } catch (SQLException e) {
            System.out.println("事务回滚"+e.getSQLState()+"---"+e.getErrorCode());
        }
    }
}
