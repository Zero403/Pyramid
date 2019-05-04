package com.mit.pyramid.common.vo;

public class CheckVO {
    private Integer id;
    private Integer uid;
    private String uname;
    private String phone;
    private String Level;
    private Integer checking;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLevel() {
        return Level;
    }

    public void setLevel(String level) {
        Level = level;
    }

    public Integer getChecking() {
        return checking;
    }

    public void setChecking(Integer checking) {
        this.checking = checking;
    }
}
