package org.example.springFactory;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @Classname BeanFactoryTest
 * @Description TODO
 * @Date 2025/2/18 下午3:24
 * @Created by SunMengyuan
 */
@Configuration
public class BeanFactoryTest {
    /**
     * 1. BeanFactory 和 FactoryBean 的区别？
     * BeanFactory： 顶级接口，用于创建Bean
     * FactoryBean：是一个接口，表示一个 Bean，用于创建需要复杂对象，例如 代理 Bean
     * @param args
     */
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanFactoryTest.class);
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        /**
         * context
         * 1、context 是上下文对象，是 BeanFactory 的实现类，Spring 的默认上下文对象，用于创建、存储、获取、管理 Bean 对象
         * 2、context 中有一个属性，private final DefaultListableBeanFactory beanFactory; 这个属性用于 Bean对象 的管理
         * 3、GenericApplicationContext 继承了 AbstractApplicationContext 抽象类。
         *    抽象类 AbstractApplicationContext，里面的 refresh() 定义了执行的具体流程。运用了模板方法设计模式。
         *    GenericApplicationContext 没有重写refresh()
         * 4、
         */
        if (context instanceof GenericApplicationContext) {
            System.out.println("context is GenericApplicationContext instance.");
        }
        if (beanFactory instanceof DefaultListableBeanFactory) {
            System.out.println("beanFactory is DefaultListableBeanFactory instance.");
        }
    }
}
