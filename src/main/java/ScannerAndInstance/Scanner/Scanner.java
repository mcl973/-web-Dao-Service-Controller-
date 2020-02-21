/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: Scanner
 * Author:   Administrator
 * Date:     2020/02/18 11:00
 * Description: 扫描
 * History:
 * <author>          <time>          <version>          <desc>
 */
package ScannerAndInstance.Scanner;

import ScannerAndInstance.AbstractBean;

import java.io.File;
import java.net.URL;

/**
 * 〈一句话功能简述〉<br> 
 * 〈扫描〉
 *
 * @author Administrator
 * @create 2020/02/18
 * @since 1.0.0
 */
public class Scanner extends AbstractScannerBean{

    //扫描整个项目，将所有的文件都拉取到filesname列表中
    public void ScannerClassFile(String path){
        //获得路径的url
        URL resource = this.getClass().getClassLoader().getResource(path.replaceAll("\\.", "/"));
        if (resource==null)
            return;
        String filename = resource.getFile();
        File file = new File(filename);
        if (file.isDirectory()){
            String[] filenames = file.list();
            if (filenames!=null) {
                for (String s : filenames) {
                    File file1 = new File(filename +"\\"+ s);
                    if (file1.isDirectory()) {
                        //递归查询
                        if (path.equals(""))
                            ScannerClassFile(s);
                        else
                            ScannerClassFile(path + "." + s);
                    } else {
                        if (path.equals(""))
                            filesname.add(s);
                        else
                            filesname.add(path + "." + s);
                    }
                }
            }
        }
    }
}
