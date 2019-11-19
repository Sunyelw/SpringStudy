package dto;

import dto.inter.Server;
import org.springframework.stereotype.Component;

/**
 * 项目名称:   pinkstone
 * 包:        dto
 * 类名称:     HttpServer
 * 类描述:     基于接口编程的@Enable 模块驱动 - 实现类1
 * 创建人:     huangyang
 * 创建时间:   2019/11/17 18:01
 */
@Component
public class HttpServer implements Server {

    @Override
    public void start(){
        System.out.println("http server start");
    }

    @Override
    public void end(){
        System.out.println("http server end..");
    }
}
