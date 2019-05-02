package com.mit.pyramid.common.vo;

import lombok.Data;

import java.util.Date;

@Data
public class UserLevelDownVO {
    private Integer lid;
    private String reason;
    private Date starttime;
    private String username;
    private String name;
}
