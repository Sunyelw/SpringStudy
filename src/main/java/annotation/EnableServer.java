package annotation;

import dao.ServerImportBeanDefinitionRegistrar;
import dao.ServerImportSelector;
import dto.Server;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 项目名称:   pinkstone
 * 包:        annotation
 * 类名称:     EnableServer
 * 类描述:     基于接口编程实现的 @Enable 驱动模式 - 实际注解
 * 创建人:     huangyang
 * 创建时间:   2019/11/17 18:07
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ServerImportBeanDefinitionRegistrar.class)
public @interface EnableServer {

    /**
     * 设置服务器类型
     */
    Server.Type type();
}
