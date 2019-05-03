package one;

import dto.MyUser;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import process.ProcessDefaultA;

/**
 * 项目名称:   pinkstone
 * 包:        one
 * 类名称:     XmlReader
 * 类描述:     XmlBeanFactory demo
 * 创建人:     huangyang
 * 创建时间:   2019/5/1 10:58
 */
public class IocContainer {

    public static void main ( String[] args ) {

        System.out.println ("=======");

//        listableBeanGet();

        applicationContextGet();
    }

    /**
     * ApplicationContext容器：
     */
    static void applicationContextGet() {

        System.out.println ( "applicationContextGet..." );

        ApplicationContext context = new FileSystemXmlApplicationContext ( "src/main/resources/myBean.xml" );

        MyUser my = (MyUser) context.getBean ( "myUser" );

//        System.out.println (null == my ? "null" : my.getName () );

    }

    /**
     * BeanFactory容器： DefaultListableBeanFactory
     */
    static void listableBeanGet() {

        System.out.println (" listableBeanGet... ");

        // 1、先拿到 代表 BeanDefinition 的 Resource 对象(这里是文件形式)
        ClassPathResource res = new ClassPathResource ( "myBean.xml" );

        // 2、构建容器，这里用的 BeanFactory 系列
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory ();

        // 添加后置处理器，BeanFactory只能手动添加
        factory.addBeanPostProcessor (new ProcessDefaultA ());
//        factory.addBeanPostProcessor (new ProcessDefaultOne ());

        // 3、将容器 与 BeanDefinition读取器 绑定起来  + BeanDefinitionRegistry
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader ( factory );

        // 4、根据Resource 对象拿到 BeanDefinition，并通过回调（？）的方式注入到容器中
        System.out.println ( " scan bean num: " + reader.loadBeanDefinitions ( res ) );

        factory.getBean ("myUser");

        factory.destroySingleton ("myUser");
    }

}
