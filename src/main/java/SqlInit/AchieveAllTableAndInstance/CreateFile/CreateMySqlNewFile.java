/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: CreateMySqlNewFile
 * Author:   Administrator
 * Date:     2020/02/16 11:52
 * Description: 创建文件
 * History:
 * <author>          <time>          <version>          <desc>
 */
package SqlInit.AchieveAllTableAndInstance.CreateFile;

import SqlInit.AchieveAllTableAndInstance.Instance;

import java.io.*;
import java.net.URL;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈创建文件〉
 *
 * @author Administrator
 * @create 2020/02/16
 * @since 1.0.0
 */
public class CreateMySqlNewFile implements Instance {
    private static String absloutpath = "";
    @Override
    public void instance(String classname, Map<String,Integer> fieldmap) {
        //创建java文件
        String result = new CreateJavaFile().getjavaclass(classname,fieldmap,"Dao");
        String sqlresult = new CreateJavaSqlFile().getjavaclass(classname,fieldmap,"DaoSql");
        String path = getnewpath(getappth());
        File file = null;
        try {
            file = createFile(path + "\\Dao\\" ,classname + ".java");
            savefile(file,result);
            file = createFile(path+"\\DaoSql\\",classname+"_sql.java");
            savefile(file,sqlresult);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public String getnewpath(String path){
        String[] outs = path.split("out");
        String startpath = outs[0]+"\\src";
        String name = this.getClass().getPackage().getName();
        char[] chars = name.toCharArray();
        String endpath = "";
        for (char aChar : chars) {
            if (aChar == '.')
                endpath+="\\";
            else endpath+=aChar;
        }
        return startpath+"\\"+endpath;
    }
    public boolean savefile(File file,String context){
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(context);
            fileWriter.flush();
            fileWriter.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    public File createFile(String path,String filename) throws IOException {
        File file = new File(path);
        if (!file.exists())
            file.mkdirs();
        File f = new File(path+"\\"+filename);
        if (!f.exists()) {
            if (f.createNewFile())
                return f;
            else System.out.println("文件创建失败");
        }else return f;
        return null;
    }
    public String getappth(){
        if (absloutpath.equals("")){
            synchronized (this){
                if (absloutpath.equals("")){
                    URL resource = this.getClass().getResource("");
                    String file = resource.getFile();
                    File f = new File(file);
                    absloutpath = f.getAbsolutePath();
                    return absloutpath;
                }else return absloutpath;
            }
        }else return absloutpath;
    }
}
