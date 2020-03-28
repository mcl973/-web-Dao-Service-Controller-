/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: TestCompolent
 * Author:   Administrator
 * Date:     2020/03/28 12:22
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */
package MyController;

import Annotation_Collection.Aop.Around;
import Annotation_Collection.NormalBean.Compolent;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2020/03/28
 * @since 1.0.0
 */
@Compolent("TestCompolent")
public class TestCompolent implements testInterface{
    @Around("MyService.AopMethods.Around.AroundAop")
    public void test(){
        System.out.println("this is compolent test");
    }
}
