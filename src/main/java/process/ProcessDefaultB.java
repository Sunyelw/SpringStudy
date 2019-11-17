package process;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;

/**
 * 项目名称:   pinkstone
 * 包:        one
 * 类名称:     ProcessDefault
 * 类描述:     beanPostProcessor
 * 创建人:     huangyang
 * 创建时间:   2019/5/2 8:32
 */
public class ProcessDefaultB implements BeanPostProcessor, Ordered {

    // before
    @Override
    public Object postProcessBeforeInitialization (Object bean, String beanName) throws BeansException {

        System.out.println ("zero++++++++++++++++++++++++");

        return bean;
    }

    // after
    @Override
    public Object postProcessAfterInitialization (Object bean, String beanName) throws BeansException {

        System.out.println ("one++++++++++++++++++++");

        return bean;
    }

    /**
     * 后置处理器的加载顺序
     *
     * @return
     */
    @Override
    public int getOrder () {
        return 1;
    }
}
