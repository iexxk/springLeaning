package com.exxk.aop;

import java.lang.annotation.*;

@Target({ ElementType.PARAMETER, ElementType.METHOD,ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Log {
    String tag() default "";
}
