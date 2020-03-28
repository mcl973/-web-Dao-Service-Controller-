/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: Autowrite
 * Author:   Administrator
 * Date:     2020/02/18 12:30
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */
package Annotation_Collection.JAutowrite;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2020/02/18
 * @since 1.0.0
 */
/*
    在给一个属性防止这个注解时必须要填写value的值。
    相当于是将原本的spring中的autowrite和quwaire的功能给集中了。
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Autowrite {
    String value()default "";
}
