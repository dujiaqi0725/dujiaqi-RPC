package com.dujiaqi.example.common.model;

import java.io.Serializable;

/**
 * 用户
 */
public class User implements Serializable {

    private String name;

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
