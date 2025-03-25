package com.huawei.bu.entity;

/**
 * @Classname User
 * @Description TODO
 * @Date 2025/3/21 下午10:01
 * @Created by SunMengyuan
 */
public class User {
    private String name;
    private Integer age;
    private Address address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
