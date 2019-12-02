package one;

import component.ConditionalMessageConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 项目名称:   pinkstone
 * 包:        one
 * 类名称:     ConditionalMainClass
 * 类描述:     条件装配测试主类
 * 创建人:     huangyang
 * 创建时间:   2019/11/26 22:19
 */
public class ConditionalMainClass {

    public static void main(String[] args){

//        System.setProperty("language", "chinese");
        System.setProperty("language", "English");

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(ConditionalMessageConfiguration.class);

        context.refresh();

        String message = context.getBean("message", String.class);

        System.out.println(message);

    }

}
