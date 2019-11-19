package importor;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.stream.Stream;

/**
 * 项目名称:   pinkstone
 * 包:        importor
 * 类名称:     ServerImportSelector
 * 类描述:     基于接口编程实现的 @Enable 驱动模式 - 注解具体实现
 * 创建人:     huangyang
 * 创建时间:   2019/11/17 18:04
 */
public class ServerImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry){

        ImportSelector importSelector = new ServerImportSelector();

        // 筛选 class 名称集合
        String[] selectedClassNames = importSelector.selectImports(importingClassMetadata);

        Stream.of(selectedClassNames)
                // 转化为 BeanDefinitionBuilder
                .map(BeanDefinitionBuilder::genericBeanDefinition)
                // 转化为 BeanDefinition
                .map(BeanDefinitionBuilder::getBeanDefinition)
                // 注册
                .forEach(beanDefinition ->
                        BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinition, registry));

    }
}
