package org.example.springFactory.DO;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;

/**
 * @Classname Student
 * @Description TODO
 * @Date 2025/2/17 下午12:41
 * @Created by SunMengyuan
 */ // 因为使用的是 xml配置文件，所以使用注解是无效的，需要xml文件中配置
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Student implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, InitializingBean {
    private String name;
    private Integer age;

    public Student() {
        System.out.println("********* 1 实例化 ************");
        System.out.println("无参构造执行了");
    }
    public void setName(String name) {
        this.name = name;
        System.out.println("*******  2 属性赋值，通过set方法  *********");
        System.out.println("setName 执行了");
    }
    public void setAge(Integer age) {
        this.age = age;
        System.out.println("setAge 执行了");
    }

    public Integer getAge() {
        return this.age;
    }

    public String getName() {
        return this.name;
    }



    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }


    // BeanDefinitionRegistryPostProcessor - 1
//    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
//        System.out.println("postProcessBeanDefinitionRegistry执行了，BeanDefinitionRegistry 后置处理器");
//    }

    // BeanDefinitionRegistryPostProcessor的父接口BeanFactoryPostProcessor - 2
//    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
//        System.out.println("postProcessBeanFactory执行了，BeanFactory后置处理器");
//    }

    // BeanNameAware
    public void setBeanName(String s) {
        System.out.println("******** 3 Aware 接口************");
        System.out.println("BeanNameAware接口的setBeanName(String s), 参数为String类型的:" + s);
    }

    // BeanFactoryAware
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("BeanFactoryAware接口的setBeanFactory(BeanFactory beanFactory):, 参数为BeanFactory类型的:" + beanFactory);
    }

    // ApplicationContextAware
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("ApplicationContextAware接口的setApplicationContext(ApplicationContext applicationContext)方法，ApplicationContext类型的参数为:" + applicationContext);
    }

    // InitializingBean 接口
    public void afterPropertiesSet() throws Exception {
        System.out.println("********** 5 InitializingBean接口 *****************");
        System.out.println("InitializingBean接口的afterPropertiesSet()方法。属性set完之后，执行的动作逻辑。");
    }

    // 自定义初始化方法
    // 特点：只会执行一次，@PostConstruct 需要与扫描的注解搭配使用，否则不生效
    @PostConstruct
    public void springPostConstruct() {
        System.out.println("自定义初始化方法 @PostConstruct 执行了，在Bean后置处理器的前置处理之后进行，自定义的init方法之前");
    }

    public void init() {
        System.out.println("自定义初始化方法 init() 执行了, " + "参数:" + name);
    }
}
