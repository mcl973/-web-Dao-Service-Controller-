/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: CreateJavaFile
 * Author:   Administrator
 * Date:     2020/02/16 11:54
 * Description: 创建java文件
 * History:
 * <author>          <time>          <version>          <desc>
 */
package SqlInit.AchieveAllTableAndInstance.CreateFile;
import SqlInit.SqlFind.SqlInitNeedCommand;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
/**
 * 〈一句话功能简述〉<br> 
 * 〈创建java文件〉
 *
 * @author Administrator
 * @create 2020/02/16
 * @since 1.0.0
 */
public class CreateJavaFile extends AbstractGenerateFile{
    /*
        这个是一个dao文件
        属性+get()+set()方法
     */
    public String getjavaclass(String classname,Map<String,Integer> fieldmap,String dao){
        String packages = "package "+getPackage()+"."+dao+";";
        String javafile = packages;
        String imports = "";
        Map<String,String> nameandtype = new HashMap<>();
        /*
            获取每一个属性对应的类型，将其以 <fieldname,fieldtype>的形式储存起来
         */
        List<Object> nameAndType = getNameAndType(fieldmap);
        imports = (String)nameAndType.get(0);
        imports+="import "+getSqlAnnotation()+".*;";
        nameandtype = (Map<String, String>)nameAndType.get(1);
        javafile+=imports;
        String javafilestart = "@Dao(\""+classname+"\")\n";
        javafilestart += "public class "+classname+"{";
        javafile+=javafilestart;
        //类主体部分
        for(Map.Entry<String,String> map:nameandtype.entrySet()){
            //开始构造属性加上方法
            javafile+=getMethod(map.getKey(),map.getValue());
        }
        String javafileend = "}";
        javafile+=javafileend;

        return javafile;
    }


    public String getMethod(String fieldname,String fieldtype){
        String methodbody = "";
        methodbody+="public "+fieldtype+" "+fieldname+";\n";
        methodbody+=getMethod_of_Get(fieldname,fieldtype)+"\n";
        methodbody+=getMethod_of_Set(fieldname,fieldtype)+"\n";
        return methodbody;
    }

    public String getMethod_of_Get(String fieldname,String fieldtype){
        String methodgetbody = "public "+fieldtype+" ";
        methodgetbody+="get"+fieldname+"(){\n";
        methodgetbody+="return "+fieldname+";}";
        return methodgetbody;
    }

    public String getMethod_of_Set(String fieldname,String fieldtype){
        String methodsetbody = "public void ";
        methodsetbody+="set"+fieldname+"("+fieldtype+" args){";
        methodsetbody+="this."+fieldname+"=args;}";
        return methodsetbody;
    }

}
