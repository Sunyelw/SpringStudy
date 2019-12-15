package load;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * 项目名称:   pinkstone
 * 包:        com.example.xyz
 * 类名称:     LoaderClass
 * 类描述:     SpringBoot中读取配置文件方法
 * 创建人:     huangyang
 * 创建时间:   2019/12/4 8:58
 */
public class LoadClass {

    // 1 本地创建 META-INF/spring.factories 文件

    // 2 放置一个属性

    // 3 配置文件路径, 相对于<classpath>
    private static final String FACTORIES_RESOURCE_LOCATION = "META-INF/spring.factories";

    public static void main(String[] args){

        System.out.println(getClassName());

        try {
            Enumeration<URL> urls = ClassLoader.getSystemResources(FACTORIES_RESOURCE_LOCATION);

            List<String> list = new ArrayList <>();
            if (urls.hasMoreElements()) {
                // 1 URL -> Properties
                URL url = urls.nextElement();
                UrlResource resource = new UrlResource(url);
                Properties properties = PropertiesLoaderUtils.loadProperties(resource);

                // 2 Properties handle
                String value = properties.getProperty(getClassName());
                list.addAll(Arrays.asList(StringUtils.commaDelimitedListToStringArray(value)));
            }

            System.out.println(list.size());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * EnableAutoConfiguration
     */
    private static String getClassName() {
        return EnableAutoConfiguration.class.getName();
    }

}
