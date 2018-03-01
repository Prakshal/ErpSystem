package com.brevitaz.model;

import java.util.Arrays;
import java.util.List;

public class Role
{
    private String id;
    private String name;
    private List<Right> right;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Right> getRight() {
        return right;
    }

    public void setRight(List<Right> right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", right=" + right +
                '}';
    }
}
