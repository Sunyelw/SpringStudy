package dto;

import annotation.HyAnnotation;
import org.springframework.context.annotation.Bean;

/**
 * 项目名称:   pinkstone
 * 包:        dto
 * 类名称:     AnnoDao
 * 类描述:     自定义派生注解 DTO
 * 创建人:     huangyang
 * 创建时间:   2019/11/17 8:14
 */
@HyAnnotation(value = "hy")
public class AnnoDao {

    @Bean
    public String get() {
        return "I'm " + AnnoDao.class.getName();
    }
}
