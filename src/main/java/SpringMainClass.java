import annotation.EnableHelloAnnotation;
import annotation.EnableServer;
import dto.AnnotationDao;
import dto.inter.Server;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;

import java.io.IOException;
import java.util.stream.Stream;

/**
 * 项目名称:   pinkstone
 * 包:        PACKAGE_NAME
 * 类名称:     SpringMainClass
 * 类描述:     main class
 * 创建人:     huangyang
 * 创建时间:   2019/5/1 10:55
 */
@SpringBootApplication
@EnableHelloAnnotation
@ComponentScan(basePackages = "annotation") // 需要开启扫描
@EnableServer(type = Server.Type.HTTP) // Server.Type.FTP / Server.Type.HTTP
public class SpringMainClass {

    public static void main(String[] args){

        ApplicationContext context = new AnnotationConfigApplicationContext(SpringMainClass.class);

        Stream.of(context.getBeanDefinitionNames())
                .map(name -> "\t" + name)
                .forEach(System.out::println);
//        InterfaceEnable();

        // 启动
        // SpringApplication.run(SpringMainClass.class, args);
        new SpringApplicationBuilder(SpringMainClass.class).run(args).close();

    }

    // 基于接口驱动 @Enable 模块驱动开发
    private static void InterfaceEnable() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(SpringMainClass.class);
        context.refresh();

//        debug 出来的 beanName
//        Server server = context.getBean("dto.HttpServer", Server.class);
        // 直接通过 Class 获取 Bean
        Server server = context.getBean(Server.class);

        server.start();
        server.end();

        context.close();
    }

    // @Enable 模块 注解模式 开发
    private static void show() {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        // 注册
        context.register(SpringMainClass.class);

        // 启动
        context.refresh();

        String show = context.getBean("show", String.class);

        System.out.println("show: " + show);

        context.close();
    }

    private static void ann() {

        ApplicationContext app = new ClassPathXmlApplicationContext("myBean.xml");
        // 派生注解 + 自定义模式注解
        AnnotationDao ann = app.getBean("hy", AnnotationDao.class);
        System.out.println(ann.get());

        // 组合注解
        try {
            // 1 默认 MetadataReaderFactory 为一个 CachingMetadataReaderFactory
            MetadataReaderFactory factory = new CachingMetadataReaderFactory();

            // 2 获取 MetadataReader
            MetadataReader reader = factory.getMetadataReader(AnnotationDao.class.getName());

            // 3 拿到 AnnotationMetadata
            AnnotationMetadata annotationMetadata = reader.getAnnotationMetadata();

            // 4 获取注解
            for (String an : annotationMetadata.getAnnotationTypes()) {
                System.out.println(AnnotationDao.class.getName() + ": " + an);

                // 5 获取元注解
                for (String bn : annotationMetadata.getMetaAnnotationTypes(an)) {
                    System.out.printf("注解 @%s 元标注 @%s\n", bn, an);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
