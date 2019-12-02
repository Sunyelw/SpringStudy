package annotation;

import component.OnSystemPropertyCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * 项目名称:   pinkstone
 * 包:        annotation
 * 类名称:     spring-study
 * 类描述:     基于系统配置自动装配
 * 创建人:     huangyang
 * 创建时间:   2019/11/26 22:09
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(OnSystemPropertyCondition.class)
public @interface ConditionalOnSystemProperty {

    String name() default "";

    String value() default "";

}
