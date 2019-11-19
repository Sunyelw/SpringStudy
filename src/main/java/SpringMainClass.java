import annotation.EnableHelloAnnotation;
import annotation.EnableServer;
import dto.AnnotationDao;
import dto.inter.Server;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

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
@EnableServer(type = Server.Type.HTTP) // Server.Type.FTP / Server.Type.HTTP
@EnableWebMvc
public class SpringMainClass {

    public static void main(String[] args){
        InterfaceEnable();
    }

    // 基于接口驱动 @Enable 模块驱动开发
    private static void InterfaceEnable() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(SpringMainClass.class);
        context.refresh();

//        // debug 出来的 beanName
//        Server server = context.getBean("dto.HttpServer", Server.class);
        // 直接通过 Class 获取 Bean
        Server server = context.getBean(Server.class);

        server.start();
        server.end();
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
                    System.out.println(AnnotationDao.class.getName() + " bn: " + bn);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
