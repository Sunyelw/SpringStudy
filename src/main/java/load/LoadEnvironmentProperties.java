package load;

import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * 项目名称:   pinkstone
 * 包:        com.example.xyz
 * 类名称:     LoadProperties
 * 类描述:     SpringBoot 读取环境变量
 * 创建人:     huangyang
 * 创建时间:   2019/12/4 9:24
 */
@Configuration
public class LoadEnvironmentProperties implements EnvironmentAware {

    // 1 实现 EnvironmentAware 重写 setEnvironment(Environment) 方法

    // 2 env 中可以获取到当前环境变量及配置信息

    @Override
    public void setEnvironment(Environment env){

        List<String> excludes;

        RelaxedPropertyResolver resolver = new RelaxedPropertyResolver(
                env, "spring.autoconfigure.");
        Map<String, Object> properties = resolver.getSubProperties("exclude");
        if (properties.isEmpty()) {
            excludes = Collections.emptyList();
            System.out.println(excludes.size());
            return;
        }
        excludes = new ArrayList<>();
        for (Map.Entry<String, Object> entry : properties.entrySet()) {
            String name = entry.getKey();
            Object value = entry.getValue();
            if (name.isEmpty() || name.startsWith("[") && value != null) {
                excludes.addAll(new HashSet<>(Arrays.asList(StringUtils
                        .tokenizeToStringArray(String.valueOf(value), ","))));
            }
        }

        System.out.println("LoadProperties implement EnvironmentAware: " + excludes.toString());
    }
}
