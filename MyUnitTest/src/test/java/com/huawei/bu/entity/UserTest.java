package com.huawei.bu.entity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @Classname UserTest
 * @Description TODO
 * @Date 2025/3/21 下午10:56
 * @Created by SunMengyuan
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest
public class UserTest {
    @InjectMocks
    private User user;

    @Mock
    private Address address;

    @Mock
    private User user1;

    @Before
    public void setUp() throws Exception {
        System.out.println("每个测试执行前。。。");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("每个测试执行后。。。");
    }

    @Test
    public void getName() {
    }

    @Test
    public void setName() {
    }

    @Test
    public void getAge() {
    }

    @Test
    public void setAge() {
    }

    @Test
    public void getAddress() {
        System.out.println(user.getAddress());
        System.out.println(user1.getAddress());
    }

    @Test
    public void setAddress() {
    }
}