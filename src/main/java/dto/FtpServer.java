package dto;

import dto.inter.Server;
import org.springframework.stereotype.Component;

/**
 * 项目名称:   pinkstone
 * 包:        dto
 * 类名称:     FtpServer
 * 类描述:     基于接口编程实现的 @Enable 模块驱动 - 实现类2
 * 创建人:     huangyang
 * 创建时间:   2019/11/17 18:02
 */
@Component
public class FtpServer implements Server {

    @Override
    public void start(){
        System.out.println("ftp server start");
    }

    @Override
    public void end(){
        System.out.println("ftp server end..");
    }
}
