package com.den.we.anotation;

import java.lang.annotation.*;

/**
 * @author fatKarin
 * @date 2020/3/13 11:11
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiredParam {

    String type() default "";
}
