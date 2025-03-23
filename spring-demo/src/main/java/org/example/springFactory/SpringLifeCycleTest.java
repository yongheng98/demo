package org.example.springFactory;

import org.example.springFactory.DO.Student;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Classname SpringLifeCycleTest
 * @Description Spring生命周期测试
 * @Date 2025/2/16 下午4:48
 * @Created by SunMengyuan
 */
//@Configuration
public class SpringLifeCycleTest {
    /**
     * spring 的生命周期
     * 重要的一个类方法 org.springframework.context.support.AbstractApplicationContext#refresh()
     * 从refresh方法里，可以看到spring执行的各个周期
     * @param args
     */
    public static void main(String[] args) {
        // 1 使用配置文件
        // Spring Bean 的实例化是通过无参构造实现的，使用lombok 注解时，需要在DO类上加入无参构造的注解
        // 否则报错 Caused by: org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.example.springFactory.DO.User]: No default constructor found; nested exception is java.lang.NoSuchMethodException: org.example.springFactory.DO.User.<init>()
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml"); // new AnnotationConfigApplicationContext("org.example.springFactory");
        Student student = (Student) context.getBean("student");
        System.out.println();
        System.out.println("主方法中的输出：" + student);
    }
}

/**
 * @Classname MyBeanPostProcessor
 * @Description TODO
 * @Date 2025/2/17 下午12:31
 * @Created by SunMengyuan
 */
// 因为使用的是 xml配置文件，所以使用注解是无效的，需要xml文件中配置
// 注意：BeanPostProcessor 和 BeanDefinitionRegistryPostProcessor 这个接口冲突，实体类不能实现 BeanDefinitionRegistryPostProcessor， 否则 BeanPostProcessor不执行
//@Component
class MyBeanPostProcessor implements BeanPostProcessor {
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof Student){
            // 可以获取到所有未冲突的Bean实例和beanName(注意，这是Bean的toString不是beanName)
            System.out.println("*************** 4 BeanPostProcessor,Bean后置处理器 start ************");
            System.out.println("初始化（Initialization）前执行处理， 通常对特定的某个Bean进行处理");
            System.out.println(bean + " " +  beanName);
        }
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        // 可以获取到所有未冲突的Bean实例和beanName(注意，这是Bean的toString不是beanName),
        if (bean instanceof Student){
            System.out.println("初始化（Initialization）后执行处理， 通常对特定的某个Bean进行处理");
//            System.out.println(bean + beanName);
            System.out.println("*************** 4 BeanPostProcessor,Bean后置处理器 end ************");

        }
        return bean;
    }

}