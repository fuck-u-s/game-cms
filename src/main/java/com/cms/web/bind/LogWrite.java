package com.cms.web.bind;

import java.lang.annotation.*;

/**
 * 方法描述:日志注解
 * <p>
 * author lzh
 * version v1.0
 * date 2016/7/6 17:57
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface LogWrite {
    // 模块名称
    String module() default "";
    // 方法名
    String methods() default "";
    // 备注
    String description() default "";
}
