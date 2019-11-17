package annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 包:        annotation
 * 类名称:     spring-study
 * 类描述:     自定义注解 验证 @Component 的派生性
 * 创建人:     huangyang
 * 创建时间:   2019/11/17 8:11
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface HyAnnotation {

    @AliasFor(annotation = Component.class)
    String value();
}
