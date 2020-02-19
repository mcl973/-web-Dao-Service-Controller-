/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: AbstractGenerateFile
 * Author:   Administrator
 * Date:     2020/02/16 16:23
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */
package SqlInit.AchieveAllTableAndInstance.CreateFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2020/02/16
 * @since 1.0.0
 */
public abstract class AbstractGenerateFile {
    abstract public String getjavaclass(String classname, Map<String,Integer> fieldmap, String dao);
    public String getPackage(){
        Package aPackage = this.getClass().getPackage();
        String packagename = aPackage.getName();
        return packagename;
    }
    public String  getSqlAnnotation(){
        Package aPackage = this.getClass().getPackage();
        String name = aPackage.getName();
        String qianzhui = "";
        if (name.contains("SqlInit")){
            qianzhui = name.split("SqlInit")[0];
            if (qianzhui.equals(""))
                qianzhui = "Annotation_Collection.Sql";
            else
                qianzhui += "Annotation_Collection.Sql";
            return qianzhui;
        }
        else
            return qianzhui;
    }
    public List<Object> getNameAndType(Map<String,Integer> fieldmap){
        Map<String,String>  nameandtype = new HashMap<>();
        String imports = "";
        List<Object> list=new ArrayList<>();
        for(Map.Entry<String,Integer> map:fieldmap.entrySet()){
            String type = SqlTypeToJavaType.getType(map.getValue());
            if (type.contains("&")){
                String[] split = type.split("&");
                imports+=split[1]+"\n";
                nameandtype.put(map.getKey(),split[0]);
            }
            else nameandtype.put(map.getKey(),type);
        }
        list.add(imports);
        list.add(nameandtype);
        return list;
    }

}
