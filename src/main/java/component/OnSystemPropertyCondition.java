package component;

import annotation.ConditionalOnSystemProperty;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

/**
 * 项目名称:   pinkstone
 * 包:        component
 * 类名称:     OnSystemPropertyCondition
 * 类描述:     基于系统属性-条件
 * 创建人:     huangyang
 * 创建时间:   2019/11/26 22:11
 */
public class OnSystemPropertyCondition implements Condition{

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata){

        Map<String, Object> map = metadata.getAnnotationAttributes(ConditionalOnSystemProperty.class.getName());

        String proName = (String) map.get("name");
        String proValue = (String) map.get("value");

        String sysValue = System.getProperty(proName);

        return proValue.equals(sysValue);
    }
}
