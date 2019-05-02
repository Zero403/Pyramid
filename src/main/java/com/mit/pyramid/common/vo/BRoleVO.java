package com.mit.pyramid.common.vo;

import com.mit.pyramid.entity.BPermission;

import java.util.List;

/**
 * @Author: Ery
 * @Date: 2019/5/2 15:18
 * @Version null
 */
public class BRoleVO {
    private Integer id;
    private String info;
    private String name;
    private List<BPermission> permissions;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<BPermission> permissions) {
        this.permissions = permissions;
    }
}
