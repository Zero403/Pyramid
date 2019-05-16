package com.mit.pyramid.common.vo;

public class CheckVO {

    private Integer id;
    private String uname;
    private String phone;
    private String sname;
    private Integer sid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public final String checkingToString() {
        return "用户昵称：" + uname  +
                ", 电话" + phone  +
                ", 用户等级" + sname +
                "\n请联系此人对您的升级进行审核";
    }
}
