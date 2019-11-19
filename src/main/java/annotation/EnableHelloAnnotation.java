package annotation;

import component.HelloConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 项目名称:   pinkstone
 * 包:        annotation
 * 类名称:     EnableHelloAnnotation
 * 类描述:     自定义 @Enable 注解
 * 创建人:     huangyang
 * 创建时间:   2019/11/17 17:12
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(HelloConfiguration.class)
public @interface EnableHelloAnnotation {
}
