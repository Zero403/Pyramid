package com.mit.pyramid.common.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class BUserBasicVO {

    private Integer id;
    private String username;
    private String phone;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date createdate;
    private Integer inviterid;
    /**
     * 0 非正式， 1 正常 ， 2违规
     */
    private Integer flag;
    private String infomation;
    private String headpic;
    private Integer status;
    private String sname;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Integer getInviterid() {
        return inviterid;
    }

    public void setInviterid(Integer inviterid) {
        this.inviterid = inviterid;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getInfomation() {
        return infomation;
    }

    public void setInfomation(String infomation) {
        this.infomation = infomation;
    }

    public String getHeadpic() {
        return headpic;
    }

    public void setHeadpic(String headpic) {
        this.headpic = headpic;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }
}
