/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: AbstractHandleAutowrite
 * Author:   Administrator
 * Date:     2020/02/19 11:01
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */
package ScannerAndInstance.HandleAutowriteEvent;

import ScannerAndInstance.AbstractBean;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2020/02/19
 * @since 1.0.0
 */
public abstract class AbstractHandleAutowrite extends AbstractBean {
    public AbstractHandleAutowrite(){
        this.doAutowrite();
    }
   abstract public void doAutowrite();
}
