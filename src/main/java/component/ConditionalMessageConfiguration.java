package component;

import annotation.ConditionalOnSystemProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 项目名称:   pinkstone
 * 包:        component
 * 类名称:     ConditionalMessageConfiguration
 * 类描述:     条件装配测试
 * 创建人:     huangyang
 * 创建时间:   2019/11/26 22:16
 */
@Configuration
public class ConditionalMessageConfiguration {

    @ConditionalOnSystemProperty(name = "language", value = "chinese")
    @Bean("message")
    public String chinese() {
        return "你好。";
    }

    @ConditionalOnSystemProperty(name = "language", value = "English")
    @Bean("message")
    public String english() {
        return "hello.";
    }

}
