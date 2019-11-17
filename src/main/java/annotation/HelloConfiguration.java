package annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 项目名称:   pinkstone
 * 包:        annotation
 * 类名称:     HelloConfiguration
 * 类描述:     @Enable 模式的 配置类， 被@Import 导入
 * 创建人:     huangyang
 * 创建时间:   2019/11/17 17:11
 */
@Component // @Configuration 也行
public class HelloConfiguration {

    @Bean
    public String show() {
        return "hello enable annotation...";
    }

}
