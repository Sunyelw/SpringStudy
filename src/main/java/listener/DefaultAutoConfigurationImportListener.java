package listener;

import org.springframework.boot.autoconfigure.AutoConfigurationImportEvent;
import org.springframework.boot.autoconfigure.AutoConfigurationImportListener;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.io.support.SpringFactoriesLoader;

import java.util.List;
import java.util.Set;

/**
 * 项目名称:   pinkstone
 * 包:        com.example.xyz.listener
 * 类名称:     DefaultAutoConfigurationImportListener
 * 类描述:     自定义SpringBoot监听事件实现
 * 创建人:     huangyang
 * 创建时间:   2019/12/15 13:31
 */
public class DefaultAutoConfigurationImportListener implements AutoConfigurationImportListener {

    @Override
    public void onAutoConfigurationImportEvent(AutoConfigurationImportEvent event){

        // 1.获取类加载器
        ClassLoader loader = event.getClass().getClassLoader();
        // 2.候选类集合名单
        List<String> candidates = SpringFactoriesLoader.loadFactoryNames(EnableAutoConfiguration.class, loader);
        // 3.实际装配类名单
        List<String> actuals = event.getCandidateConfigurations();
        // 4.获取排除类名单
        Set<String> excludes = event.getExclusions();

        System.out.printf("候选类数量: %s \n", candidates.size());
        System.out.printf("装配类数量: %s \n", actuals.size());
        System.out.printf("排除类数量: %s \n", excludes.size());

        // print one
        System.out.println("实际装配类名单:");
        actuals.forEach(System.out::println);

        // print two
        System.out.println("获取排除类名单:");
        excludes.forEach(System.out::println);
    }
}
