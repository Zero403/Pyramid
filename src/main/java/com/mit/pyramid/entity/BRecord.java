package com.mit.pyramid.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.util.Date;

/**
 */
@TableName("b_record")
@Data
public class BRecord extends Model<BUserLevelDown> {


    @TableId(value="id", type= IdType.AUTO)
    private Integer id;
    private Integer type;
    private Integer uid;
    private Integer cid;
    private String content;
    private Date createtime;


}
