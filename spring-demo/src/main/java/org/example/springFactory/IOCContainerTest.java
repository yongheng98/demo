package org.example.springFactory;

import org.example.springFactory.DO.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
* @Classname IOCContainerTest
* @Description 创建 IOC 容器/ 上下文对象，并获取 Bean 对象
 * 下面是两种常见的创建IOC容器的方式
 * 1. ClassPathXmlApplicationContext：从类路径下加载配置文件
 * 注意事项：需要在pom文件中指定加载resources目录，否则在target中会找不到配置文件，报错: Could not open ServletContext resource [/application.xml]
 * 2. AnnotationConfigApplicationContext：从注解配置中加载配置文件，和注解搭配，需要指定 basePackages
 * 两者不能同时执行，一个spring项目中只能有一个 IOC 容器
* @Date 2025/2/16 下午3:39
* @Created by SunMengyuan
*/
@Configuration
public class IOCContainerTest {
    public static void main(String[] args) {
        // 1 加载配置文件，创建 IOC 容器
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
//        User user = (User)context.getBean("user");
//        System.out.println(user);
        /**
         * 2 ApplicationContext 是一个接口, 规定了IOC容器的功能
         * AnnotationConfigApplicationContext 是 ApplicationContext 的实现类, 可以读取注解配置的 Bean
         * 注意：需要指定包名 basePackages，否则会报错: org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named 'user' available
         */
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("org.example.springFactory");
        User user1 = (User)applicationContext.getBean("user");
        System.out.println(user1);
    }

    /**
     * ApplicationContext 读取
     * @return
     */
    @Bean
    public User user(){
        return new User("sun", 18);
    }
}
