package SqlInit.AchieveAllTableAndInstance.CreateFile;

import java.util.Map;

public interface CreateMethods {
    //初始化单个查询语句函数
    public String getSelectForId(String classname, String methodfield, Map<String, String> fieldmap);
    //初始化查询语句函数
    public String getSelectForMore(String classname);
    //初始化更新语句函数
    public String GetUpdateMethod(String classname);
    //初始化删除语句函数
    public String GetDeleteMethod(String classname);
    //初始化插入语句函数
    public String GetInsertMethod(String classname);
    //初始化编写函数中的一个查询记录函数
    public String GetOneSelectRecord();
    //批量删除
    public String getBatchMethod();
}
