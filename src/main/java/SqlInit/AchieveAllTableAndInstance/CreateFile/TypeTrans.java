/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: TypeTrans
 * Author:   Administrator
 * Date:     2020/02/16 17:52
 * Description: String 类型转换
 * History:
 * <author>          <time>          <version>          <desc>
 */
package SqlInit.AchieveAllTableAndInstance.CreateFile;

import sun.security.krb5.internal.PAEncTSEnc;

import java.lang.reflect.Parameter;
import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

/**
 * 〈一句话功能简述〉<br> 
 * 〈String 类型转换〉
 *
 * @author Administrator
 * @create 2020/02/16
 * @since 1.0.0
 */
public class TypeTrans {
    public static Object getvalue(String stringvalue,String value){
        if (stringvalue.contains(".")){
            char[] chars = stringvalue.toCharArray();
            String temp = "";
            for (char aChar : chars) {
                if (aChar=='.') {
                    temp = "";
                }
                else
                    temp+=aChar;
            }
            stringvalue = temp;
        }
        switch (stringvalue){
            case "String":return value;
            case "byte[]":return value.getBytes();
            case "long": return Long.parseLong(value);
            case "int": return Integer.parseInt(value);
            case "boolean":return Boolean.valueOf(value);
            case "float":return Float.parseFloat(value);
            case "double":return Double.parseDouble(value);
            case "Date":return strToDate(value);
            case "BigInteger":return new BigInteger(value);
            case "Timestamp":return Timestamp.valueOf(value);
            case "":return Time.valueOf(value);
            default:return "";
        }
    }

    public static Date strToDate(String strDate) {
          SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
          ParsePosition pos = new ParsePosition(0);
          Date strtodate = formatter.parse(strDate, pos);
          return strtodate;
    }
}
