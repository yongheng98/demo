package com.huawei.bu.service;

import com.huawei.bu.entity.Address;
import com.huawei.bu.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * @Classname UserService
 * @Description TODO
 * @Date 2025/3/22 上午9:44
 * @Created by SunMengyuan
 */
public class UserService {

    List<User> userList = new ArrayList<User>();

    private void privateVoidMethod(){
        System.out.println("UserService do privateMethod()");
    }

    public void arg1(Address address){
        setUserAddress(address);
    }

    private void privateMethod1(Integer code){
//        int code = 1;
        if (code == 1){
            System.out.println("UserService do privateMethod1");
        } else {
            privateMethod2();
        }
    }
    private void privateMethod2(){
        System.out.println("UserService do privateMethod2");
    }

    public void publicMethod(int code){
        privateMethod1(code);
    }

    /**
     * 获取全部的User
     * @return
     */
    public List<User> getUserList(){
        // 调用本类的私有方法
        privateVoidMethod();
        if (!userList.isEmpty()){
            return userList;
        }
        userList.add(new User());
        return userList;
    }

    public String lianXu(){
        String aaa = "sdf";
        aaa = "564";
        return "";
    }

    public void thread(){
        Executors.newCachedThreadPool().execute(() -> {
            int a = 1;
            int b = 2;
            String c = "a";
            System.out.println(a + b + c);
        });
    }

    public void setUserAge(Integer age){
        User user = new User();
        user.setAge(age);
    }

    public void setUserAddress(Address address){
        User user = new User();
        user.setAddress(address);
    }


}
