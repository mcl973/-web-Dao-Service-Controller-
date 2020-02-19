/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: SqlTypeToJavaType
 * Author:   Administrator
 * Date:     2020/02/16 12:43
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */
package SqlInit.AchieveAllTableAndInstance.CreateFile;


/**
 * 〈一句话功能简述〉<br> 
 * 〈sql的type对应于java的type〉
 *
 * @author Administrator
 * @create 2020/02/16
 * @since 1.0.0
 */
public class SqlTypeToJavaType {
    /*
    java.lang.String	12
    java.lang.String	1
    java.lang.String	-1
    java.lang.byte[]	-4
    java.lang.Long	     4
    java.lang.Long	     4
    Tjava.lang.Integer	-6
    java.lang.Integer	5
    java.lang.Integer	4
    java.lang.Boolean	-7
    java.math.BigInteger	-5
    java.lang.Float	      7
    java.lang.Double	8
    java.math.BigDecima  3
    java.sql.Date	91
    java.sql.Date	91
    java.sql.Time	92
    java.sql.Timestamp	93
    java.sql.Timestamp	93
 */
    public static String getType(int type){
        switch(type){
            case 12:
            case -1:
            case 1 : return "String";
            case -4: return "byte[]";
            case 4: return "long";
            case -6:
            case 5: return "int";
            case -7:return "boolean";
            case -5:return "BigInteger&import java.math.BigInteger;";
            case 7:return "float";
            case 8:return "double";
            case 3:return "BigDecimal&import java.math.BigDecimal;";
            case 91:return "Date&import java.sql.Date;";
            case 92:return "Time&import java.sql.Time;";
            case 93:return "Timestamp&import java.sql.Timestamp;";
            default:return "";
        }
    }
}
