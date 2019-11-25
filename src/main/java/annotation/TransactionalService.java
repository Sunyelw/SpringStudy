package annotation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 项目名称:   pinkstone
 * 包:        annotation
 * 类名称:     spring-study
 * 类描述:     Spring模式注解 + Spring功能注解
 * 创建人:     huangyang
 * 创建时间:   2019/11/25 19:34
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Transactional
@Service
public @interface TransactionalService {

    /**
     * @return Bean name
     */
    String name() default "";

}
