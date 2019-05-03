package com.mit.pyramid.common.vo;

import com.mit.pyramid.entity.BPermission;
import com.mit.pyramid.entity.BUser;

import java.util.List;

/**
 * @Author: Ery
 * @Date: 2019/5/3 15:32
 * @Version null
 */
public class MenuVo {
    private String parentName;
    private List<PermissionVO> permissions;

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public List<PermissionVO> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionVO> permissions) {
        this.permissions = permissions;
    }
}
