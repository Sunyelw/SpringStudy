import annotation.EnableHelloAnnotation;
import dto.AnnoDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 项目名称:   pinkstone
 * 包:        PACKAGE_NAME
 * 类名称:     SpringMainClass
 * 类描述:     main class
 * 创建人:     huangyang
 * 创建时间:   2019/5/1 10:55
 */
@EnableHelloAnnotation
@ComponentScan(basePackages = "annotation") // 需要开启扫描
public class SpringMainClass {

    public static void main(String[] args){

        show();
    }

    // @Enable 模块 注解模式 开发
    private static void show() {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(SpringMainClass.class);

        context.refresh();

        String show = context.getBean("show", String.class);

        System.out.println("show: " + show);

    }


    private static void anno() {
        // 派生注解
        ApplicationContext app = new ClassPathXmlApplicationContext("myBean.xml");
        AnnoDao ann = (AnnoDao) app.getBean("hy");
        System.out.println(ann.get());

        // 组合注解
        try {
            // 1 默认 MetadataReaderFactory 为一个 CachingMetadataReaderFactory
            MetadataReaderFactory factory = new CachingMetadataReaderFactory();

            // 2 获取 MetadataReader
            MetadataReader reader = factory.getMetadataReader(AnnoDao.class.getName());

            // 3 拿到 AnnotationMetadata
            AnnotationMetadata annotationMetadata = reader.getAnnotationMetadata();

            // 4 获取注解
            for (String an : annotationMetadata.getAnnotationTypes()) {
                System.out.println(AnnoDao.class.getName() + ": " + an);

                // 5 获取元注解
                for (String bn : annotationMetadata.getMetaAnnotationTypes(an)) {
                    System.out.println(AnnoDao.class.getName() + " bn: " + bn);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
