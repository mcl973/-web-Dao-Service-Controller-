/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: AbstractHandleAopBean
 * Author:   Administrator
 * Date:     2020/02/19 10:18
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */
package ScannerAndInstance.HanleAopEvent;

import ScannerAndInstance.AbstractBean;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2020/02/19
 * @since 1.0.0
 */
public abstract class AbstractHandleAopBean extends AbstractBean {
    public AbstractHandleAopBean(){
        this.instanceaop();
    }
    abstract void instanceaop();
}
