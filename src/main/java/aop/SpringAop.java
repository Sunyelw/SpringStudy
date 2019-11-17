package aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * 项目名称:   pinkstone
 * 包:        aop
 * 类名称:     SpringAop
 * 类描述:     spring aop
 * 创建人:     huangyang
 * 创建时间:   2019/10/27 17:54
 */
@Aspect
public class SpringAop {


    @Before("execution(* show())")
    public void hyBefore() {

    }
}


