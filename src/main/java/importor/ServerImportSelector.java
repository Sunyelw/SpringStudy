package importor;

import annotation.EnableServer;
import dto.FtpServer;
import dto.HttpServer;
import dto.inter.Server;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;
import java.util.Set;

/**
 * 项目名称:   pinkstone
 * 包:        importor
 * 类名称:     ServerImportSelector
 * 类描述:     基于接口编程实现的 @Enable 驱动模式 - 注解具体实现
 * 创建人:     huangyang
 * 创建时间:   2019/11/17 18:04
 */
public class ServerImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata){

        Set<String> annotations = importingClassMetadata.getAnnotationTypes();

        for (String an : annotations) {
            if (an.equals(EnableServer.class.getName())) {
                Map<String, Object> valueMap = importingClassMetadata.getAnnotationAttributes(an);

                Server.Type type = (Server.Type) valueMap.get("type");
                switch (type) {
                    case HTTP:
                        return new String[]{ HttpServer.class.getName() };
                    case FTP:
                        return new String[]{ FtpServer.class.getName() };
                }

            }
        }
        return new String[0];
    }
}
