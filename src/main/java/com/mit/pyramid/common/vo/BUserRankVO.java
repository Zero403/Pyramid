package com.mit.pyramid.common.vo;

public class BUserRankVO {

    private Integer id;
    private Integer uid;
    private String username;
    private Integer invitenumbers;
    private Integer sid;
    private String sname;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getInvitenumbers() {
        return invitenumbers;
    }

    public void setInvitenumbers(Integer invitenumbers) {
        this.invitenumbers = invitenumbers;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }
}
