package com.huawei.bu.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.io.IOException;

/**
 * @Classname UserServiceTest
 * @Description TODO
 * @Date 2025/3/25 下午10:37
 * @Created by SunMengyuan
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserService userService;

    @Before
    public void setUp(){
        userService = new UserService();
    }
    /**
     * 1 静态方法mock 打桩 done
     * 2 私有方法mock 打桩 todo
     * 3 file类的mock 打桩 done
     */
    @Test
    public void test1() throws IOException {
        // 1 mock 没有覆盖到
//        MockedStatic<UserService> mockedStatic =  Mockito.mockStatic(UserService.class);
//        mockedStatic.when(UserService::file).thenAnswer(invocationOnMock -> {
//            System.out.println(1);
//            return null;
//        });

        // https://www.baeldung-cn.com/mockito-mock-static-methods
        // 2 断点可以过去，没有覆盖到. thenThrow可以
        try (MockedStatic<UserService> utilities = Mockito.mockStatic(UserService.class)) {
            // 模拟 StaticUtils.name()方法  // 打桩生效
            utilities.when(UserService::file).thenAnswer(invocationOnMock -> {
                return null;
            });
        }
        // 执行，使用实例或者类名执行方法  离开mock作用域后调用的是真实的方法
        UserService.file();
        // 静态验证 verify 验证成功, 但是没有覆盖率
//        verifyStatic(()->UserService.file(), times(1));
    }

    /**
     * 反射是不是ok，私有方法打不了stub todo
     * File类的mock
     * File 不能方法一个方法中，必须放到两个方法中，即thenReturn不能连用
     */
    @Test
    public void test2(){
        MockedConstruction<File> aaa = Mockito.mockConstruction(File.class, (mock, context) -> {

            if (!"aaaad".equals(context.arguments().get(0))) {
                //清理掉无用的mock，底层是其实是一个弱引用的map存放了所有的mock对象
                Mockito.framework().clearInlineMock(mock);
                return;
            }
//            Mockito.framework().addListener(new MockitoTestListener() {
//                @Override
//                public void testFinished(TestFinishedEvent testFinishedEvent) {
//                    System.out.println("finished");
//                }
//
//                @Override
//                public void onMockCreated(Object o, MockCreationSettings mockCreationSettings) {
//                    System.out.println("created");
//                    System.out.println(o);
//                    System.out.println(mockCreationSettings);
//                }
//            });
            Mockito.when(mock.exists()).thenReturn(true);
        });
        userService.createFile();
    }
    @Test
    public void test3(){
        MockedConstruction<File> aaa = Mockito.mockConstruction(File.class, (mock, context) -> {

            if (!"aaaad".equals(context.arguments().get(0))) {
                //清理掉无用的mock，底层是其实是一个弱引用的map存放了所有的mock对象
                Mockito.framework().clearInlineMock(mock);
                return;
            }
//            Mockito.framework().addListener(new MockitoTestListener() {
//                @Override
//                public void testFinished(TestFinishedEvent testFinishedEvent) {
//                    System.out.println("finished");
//                }
//
//                @Override
//                public void onMockCreated(Object o, MockCreationSettings mockCreationSettings) {
//                    System.out.println("created");
//                    System.out.println(o);
//                    System.out.println(mockCreationSettings);
//                }
//            });
            Mockito.when(mock.exists()).thenReturn(false);
        });
        userService.createFile();
    }


}