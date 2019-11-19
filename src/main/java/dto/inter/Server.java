package dto.inter;

/**
 * 项目名称:   pinkstone
 * 包:        dto
 * 类名称:     Server
 * 类描述:     基于接口编程的 @Enable 模块驱动 - 接口类
 * 创建人:     huangyang
 * 创建时间:   2019/11/17 17:59
 */
public interface Server {

    // 开始
    void start();

    // 结束
    void end();

    /**
     * 类型
     */
    enum Type {

        HTTP,

        FTP
    }

}
