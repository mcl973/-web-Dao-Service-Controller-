/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: Paragrame
 * Author:   Administrator
 * Date:     2020/02/18 11:37
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */
package Annotation_Collection.JParagrame;

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
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface Paragrame {
    String value()default "";
}
