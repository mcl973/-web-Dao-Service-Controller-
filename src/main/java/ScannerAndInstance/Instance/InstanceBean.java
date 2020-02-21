/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: InstanceBean
 * Author:   Administrator
 * Date:     2020/02/18 11:20
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */
package ScannerAndInstance.Instance;

import Annotation_Collection.Sql.Dao;
import Annotation_Collection.Sql.DaoSql;

import java.lang.annotation.Annotation;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2020/02/18
 * @since 1.0.0
 */
public class InstanceBean extends AbstractInstanceBean {
    @Override
    public void instanceBean() {
        //开始实例化,当前只是实例化class上面的带有注释的类
        for (String s : filesname) {
            if (!s.contains(".class"))
                continue;
            String news = s.replace(".class","");
            try {
                Class<?> cl = Class.forName(news);
                if (cl.isAnnotation())
                    continue;
                //z这里应该写一个关于解读注释的策略模式
                JieXiAnnotationInterface jieXi = GetJieXi.getJieXi(cl);
                if (jieXi == null)
                    continue;
                //获取注解中的字符串
                String s1 = jieXi.JiexiAnnotation(cl);
                //如果不是null，那就执行实例化
                if (s1!=null){
                    //如果是自定义的注解，就分割myself
                    if (s1.contains("myself")){
                        s1 = s1.replaceAll("myself", "_");
                    }
                    iocmap.put(s1,cl.newInstance());
                }
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        //处理完后将filesname清空，防止占内存
        filesname = null;
    }
}
