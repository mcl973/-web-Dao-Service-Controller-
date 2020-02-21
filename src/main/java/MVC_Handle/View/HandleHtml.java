/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: HandleHtml
 * Author:   Administrator
 * Date:     2020/02/21 10:25
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */
package MVC_Handle.View;

import MVC_Handle.Model.Model;

import java.lang.reflect.Field;
import java.util.Vector;

/**
 * 〈一句话功能简述〉<br> 
 * 〈处理html〉
 *
 * @author Administrator
 * @create 2020/02/21
 * @since 1.0.0
 */
public class HandleHtml {
    /*
        总的来说时分为了三步走战略：
        1.找，局部代替
        2.分，找值代替
        3.合
     */
    public String wrapetheresult(String html,Object object,String objectname){
        //读取，找，局部代替
        String result = HtmlToString(html);
        //如果为null，或是不包含mcl######mcl，就直接返回，表示没有数据需要填充
        if (result == null||!result.contains("mcl######mcl"))
            return result;
        //按照mcl######mcl分割
        String[] split = result.split("mcl######mcl");
        Object o = null;
        if (!split[1].equals("hello")){
            //将中间的字符串编程真正的数据,这个是实现了渲染的核心。 分，找值代替
            o =handlerObject(object,objectname,split[1]);
        }
        //合并数据，合
        result = split[0]+o+split[2];
        return result;
    }

    //处理html。将${XXX}换为"mcl######mcl"+result+"mcl######mcl"
    //其中如果原本的xxx是一个Object，那么就直接是"mcl######mcl"+object+"mcl######mcl"
    //如果是object.id,那么就换成"mcl######mcl"+id+"mcl######mcl"
    private String HtmlToString(String html){
        char[] chars = html.toCharArray();
        int i = 0;
        String str = "";
        String result = "";
        for (char aChar : chars) {
            //起始点<<<<<<<<<<<<<<<<<<<<<<
            if (aChar=='$') {
                i = 1;
                continue;
            }
            if (i==1) {
                if (aChar == '{')
                    continue;
                else if (aChar == '}')
                    //终点>>>>>>>>>>>>>>>>>>>>>>>>>>
                    i= 0;
                else {
                    result+=aChar;
                }
                continue;
            }
            if (!result.equals("")) {
                //合并处理---------------------------------
                result = handlerData(result);
                str +="mcl######mcl"+result+"mcl######mcl"+aChar;//此时需要处理数据。
                result="";
            }
            else {
                str += aChar;
            }
        }
        if (!str.equals(""))
            return str;
        return null;
    }

    //处理数据
    public String handlerData(String string){
        //判断是否为null
        if (string!=null) {
            //取出点号后面的属性名,如果不包含点号那表示他就是一个字符串或是其他基本类型，直接返回
            if (string.contains("."))
                //进一步取出数据
                return getLastNam(string);
            else return string;
        }
        return "hello";
    }

    public Object handlerObject(Object object,String objectname,String result){
        Class<?> aClass = object.getClass();
        //如果object的名字等于result，也就是原本的html中的，那么就直接返回object中的数据，代替原本的result
        if (objectname.equals(result)){
            return object;
        }else {//否则找到object中的对应的属性，并将其值返回带起原本的字符串位置
            try {
                Field field = aClass.getDeclaredField(result);
                return field.get(object);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    //莫名其妙，jdk不能够使用. 来分割字符串，只能手动分割了。
    //object.id,取出id这个字符串
    public String getLastNam(String name){
        char[] chars = name.toCharArray();
        String result = "";
        for (char aChar : chars) {
            if (aChar == '.')
                result = "";
            else
                result+=aChar;
        }
        if (!result.equals(""))
            return result;
        else return null;
    }
}
