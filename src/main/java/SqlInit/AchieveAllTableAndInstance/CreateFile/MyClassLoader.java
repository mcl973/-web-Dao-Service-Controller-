/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: MyClassLoader
 * Author:   Administrator
 * Date:     2020/02/16 14:32
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */
package SqlInit.AchieveAllTableAndInstance.CreateFile;

import java.io.*;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2020/02/16
 * @since 1.0.0
 * 废弃，不使用
 */
public class MyClassLoader extends ClassLoader {
    String path = "";
    public MyClassLoader(String path){
        this.path = path;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] data = getclassdata(classNameToPath(name));
        if (data!=null){
            return defineClass(name,data,0,data.length);
        }else return null;
    }

    public byte[] getclassdata(String filepathname){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(filepathname)));
            String data ;
            while ((data=bufferedReader.readLine())!=null){
                byteArrayOutputStream.write(data.getBytes());
            }
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    private String classNameToPath(String className) {
        return path +"\\"+ className;
    }

}
