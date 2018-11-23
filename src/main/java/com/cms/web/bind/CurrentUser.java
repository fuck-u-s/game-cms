package com.cms.web.bind;

import com.cms.utils.Constants;

import java.lang.annotation.*;

/**
 * 绑定当前登录用户
 * @author Administrator
 *
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentUser {

	 String value() default Constants.CURRENT_USER;
}
