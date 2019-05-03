package com.mit.pyramid.common.vo;

/**
 * @Author: Ery
 * @Date: 2019/5/3 16:28
 * @Version null
 */
public class PermissionVO {
    private String permission;
    private String url;
    private String parentName;

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}
