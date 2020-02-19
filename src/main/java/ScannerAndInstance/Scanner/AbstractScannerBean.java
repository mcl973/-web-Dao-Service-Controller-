/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: AbstractScannerBean
 * Author:   Administrator
 * Date:     2020/02/18 11:16
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */
package ScannerAndInstance.Scanner;

import ScannerAndInstance.AbstractBean;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2020/02/18
 * @since 1.0.0
 */
public abstract class AbstractScannerBean extends AbstractBean {

    public AbstractScannerBean(){
        this.ScannerClassFile("");
    }
    //不同的扫描方式有不同的抉择，这里可以改写扫描方式，提供一种本框架实现的一种方式
    abstract public void ScannerClassFile(String path);
}
