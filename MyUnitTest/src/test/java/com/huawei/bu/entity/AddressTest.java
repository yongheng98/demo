package com.huawei.bu.entity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @Classname AddressTest
 * @Description TODO
 * @Date 2025/3/21 下午10:04
 * @Created by SunMengyuan
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest
public class AddressTest {
    @Mock
    private Address address;
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getProvince() {
        // test-1 mock coverage
        address.setCity("aaa");
//         test-2 real coverage
//        Address address1 = new Address();
//        address1.setCity("bbb");
        // test-3 spy coverage
//        Address spy = Mockito.spy(Address.class);
//        spy.setCity("ccc");
    }

    @Test
    public void setProvince() {
    }

    @Test
    public void getCity() {
    }

    @Test
    public void setCity() {
    }

    @Test
    public void getStress() {
    }

    @Test
    public void setStress() {
    }
}