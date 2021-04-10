package com.yiku.aspect.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ProjectName: kc-apiservice
 * @Package: cn.kingcar.apiservice.biz.aspect.annotation
 * @auth 10853
 * @Createon 2018/5/4.
 * Description
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InterfaceCheck {
    String name() default "";
}
