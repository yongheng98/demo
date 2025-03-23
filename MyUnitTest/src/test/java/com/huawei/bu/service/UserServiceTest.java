package com.huawei.bu.service;

import com.huawei.bu.entity.Address;
import com.huawei.bu.entity.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.api.support.membermodification.MemberModifier;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.internal.WhiteboxImpl;

import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Classname UserServiceTest
 * @Description TODO
 * @Date 2025/3/22 上午9:50
 * @Created by SunMengyuan
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({UserService.class, Executors.class})
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private List<User> userList;

    @Before
    public void setUp() throws Exception {
        System.out.println("执行前");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("执行后");
    }

    @Test
    public void getUserList() throws Exception {
        // mock依赖的其他方法的行为，打桩
        // private 方法打桩, 实际是否执行
        // 使用spy对象调用，断点可以过去。此时，coverage不会统计这个私有方法，没有覆盖到。
        UserService spy = PowerMockito.spy(userService);
//        PowerMockito.doThrow(new RuntimeException()).when(spy, "privateVoidMethod");

        // 所有的方法都没有被统计，因为是mock的对象。
//        UserService mock = PowerMockito.mock(userService.getClass());
//        PowerMockito.doNothing().when(mock, "privateVoidMethod"); // 无报错，执行次数验证失败。Wanted but not invoked userService.privateVoidMethod(); However, there was exactly 1 interaction with this mockuserService.getUserList();

        // 使用真实的对象
//        UserService userService1 = new UserService();
        Mockito.when(userList.isEmpty()).thenReturn(false);
        // 执行
        List<User> userList1 = spy.getUserList();
        // 报错。
        // org.mockito.exceptions.misusing.NotAMockException:
        //Argument passed to verify() is of type UserService and is not a mock!
        PowerMockito.verifyPrivate(spy, Mockito.times(1)).invoke("privateVoidMethod");

        Mockito.when(userList.isEmpty()).thenReturn(true);
        userList1 = spy.getUserList();
        PowerMockito.verifyPrivate(spy, Mockito.times(2)).invoke("privateVoidMethod");

        Mockito.argThat(new ArgumentMatcher<String>() {

            @Override
            public boolean matches(String s) {
                return false;
            }
        });
    }

    @Test
    public void test2() throws Exception {
//        UserService mock = PowerMockito.mock(userService.getClass());
        UserService mock = PowerMockito.spy(userService);
//        mock.getUserList();
        PowerMockito.doNothing().when(mock, "privateVoidMethod");
        mock.getUserList();
        // 验证：mock对象调用次数可以通过verify证明
//        Mockito.verify(mock,Mockito.times(2)).getUserList();
        // 验证：mock对象的private方法调用次数 不可以通过verify证明
        PowerMockito.verifyPrivate(mock, Mockito.times(1)).invoke("privateVoidMethod");

    }

    /**
     * 连续调用一个方法，可以设置多个不同的行为
     */
    @Test(expected = RuntimeException.class)
    public void testLianXu(){
        UserService spy = PowerMockito.spy(new UserService());
        Mockito.when(spy.lianXu()).thenThrow(new RuntimeException()).thenReturn("aaa");
        spy.lianXu(); // 异常
        spy.lianXu(); // aaa
        spy.lianXu(); // aaa

    }

    @Test
    public void test3 (){
        UserService mock = Mockito.mock(UserService.class);
        // 无返回值时使用这种形式，因为无返回值的函数不能作为方法的参数类型
        // doCallRealMethod mock对象可以通过doCallRealMethod进行真实调用，此时coverage会统计
//        Mockito.doReturn("aaa").when(mock).lianXu();
        Mockito.doCallRealMethod().when(mock).lianXu();
        Assert.assertEquals("", mock.lianXu());
    }

    @Test
    public void testThread(){
        //
        UserService spy = Mockito.spy(userService);
//        UserService spy = Mockito.mock(userService.getClass());
        // when
        PowerMockito.mockStatic(Executors.class);
        ExecutorService executorService = PowerMockito.mock(ExecutorService.class);
        PowerMockito.when(Executors.newCachedThreadPool()).thenReturn(executorService);
//        Mockito.doNothing().when(executorService).execute(Mockito.any(Runnable.class));
        Mockito.doAnswer(invocationOnMock -> {
            Runnable runnable = invocationOnMock.getArgument(0);
            runnable.run();
            return null;
        }).when(executorService).execute(Mockito.any(Runnable.class));
        //
        spy.thread();
    }

    @Test
    public void testArgsMatcher(){
        UserService userService1 = new UserService();
        UserService spy = Mockito.spy(userService1);
        Mockito.doAnswer(invocationOnMock -> {
            Address argument = invocationOnMock.getArgument(0);
            System.out.println(argument.getProvince());
            return null;
        }).when(spy).setUserAddress(Mockito.any(Address.class));
//        spy.setUserAge(12);
        Address address = new Address();
        address.setProvince("sdfsdf");
        spy.setUserAddress(address);
    }
    @Test
    public void testArgs(){
        UserService spy = Mockito.spy(new UserService());
        Address address = new Address();
        address.setProvince("sdfsdf");

        Mockito.doAnswer(
                invocationOnMock -> {
                    // 获得spy对象的mock对象，只能获得mock的，不能获得spy类型的。具体的方法执行，需要调用 invocationOnMock.callRealMethod();
//                    Object mock = invocationOnMock.getMock();
//                    System.out.println(mock);
                    // 获取调用时，传递的原始的参数值
                    Address argument = invocationOnMock.getArgument(0);
                    System.out.println(argument.getProvince());
                    // 修改这个原始的参数值
                    argument.setProvince("set new Province");
                    System.out.println(argument.getProvince());

//                    invocationOnMock.getMethod().invoke();
                    // 可以影响到之后的调用。如果是private的话，这里不能指定这个方法名，怎么处理呢
                    Mockito.doNothing().when(spy).setUserAddress(Mockito.any(Address.class));
                    // 用修改后的参数值调用真实public方法。可以通过这种方式，进入private方法中
                    invocationOnMock.callRealMethod();
                    return null;
                }
        ).when(spy).arg1(Mockito.any(Address.class));
        spy.arg1(address);
        Mockito.verify(spy, Mockito.times(1)).arg1(Mockito.any(Address.class));
        Mockito.verify(spy, Mockito.times(1)).setUserAddress(Mockito.any(Address.class));
    }

    /**
     * public - private - private
     * 都是无参数，无返回值函数
     * 目的是进入private 方法内部，调试
     */
    @Test
    public void testPrivate1() throws Exception {
//        PowerMockito.mockStatic(UserService.class);
        // 使用 PowerMockito.verifyPrivate验证的时候，只能使用PowerMockito创建对象
        UserService spy = PowerMockito.spy(new UserService());
        int code = 1;

        Mockito.doAnswer(invocationOnMock -> {
            System.out.println(invocationOnMock.getMethod().getName());
            System.out.println("=========");
            Method[] methods = MemberModifier.methods(UserService.class, "privateMethod1");
            Method method = methods[0];
            if (method.getName().equals("privateMethod1")){
                // 执行
//                WhiteboxImpl.invokeMethod(spy,"privateMethod1", 1);
//                WhiteboxImpl.invokeMethod(spy,"privateMethod1", 2);
                // WhiteboxImpl.invokeMethod(spy,"privateMethod1", 1); 可以被验证
//                PowerMockito.verifyPrivate(spy, Mockito.times(1)).invoke("privateMethod1", 1);
//                PowerMockito.verifyPrivate(spy, Mockito.times(1)).invoke("privateMethod1", 2);

            }
            System.out.println("==========");


//            invocationOnMock.callRealMethod();
            invocationOnMock.callRealMethod();
//            Mockito.verify(spy, Mockito.times(3)).publicMethod(code);
            return null;
        }).when(spy).publicMethod(code);

        spy.publicMethod(code);

        // 默认 1 次
        Mockito.verify(spy, Mockito.times(1)).publicMethod(code);
//        PowerMockito.verifyPrivate(spy, Mockito.times(1)).invoke("privateMethod1", code);
    }
    @Test
    public void test21() throws Exception {
        // 私有方法的spy执行,spy 和 mock 都可以执行
//        UserService spy = Mockito.spy(new UserService());
        UserService spy = Mockito.mock(UserService.class);
        // 无参数的时候，就不用传参数
        WhiteboxImpl.invokeMethod(spy,"privateMethod2");
    }
}