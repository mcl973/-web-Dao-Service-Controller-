/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName:
 * Author:   Administrator
 * Date:     2020/02/18 11:14
 * Description: 抽象的实例化
 * History:
 * <author>          <time>          <version>          <desc>
 */
package ScannerAndInstance.Instance;

import ScannerAndInstance.AbstractBean;

/**
 * 〈一句话功能简述〉<br> 
 * 〈抽象的实例化〉
 *
 * @author Administrator
 * @create 2020/02/18
 * @since 1.0.0
 */
public abstract class AbstractInstanceBean extends AbstractBean {

    public AbstractInstanceBean(){
        this.instanceBean();
    }
    abstract public void instanceBean();
}
