package zxf.springboot.checking.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface UserRightCheck {
    boolean adminOnly() default false;

    String[] rights() default {};

    String express() default "";
}
