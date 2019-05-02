package com.mit.pyramid.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.util.Date;

/**
 */
@TableName("b_user_level_down")
@Data
public class BUserLevelDown extends Model<BUserLevelDown> {


	@TableId(value="lid", type= IdType.AUTO)
	private Integer lid;
	private Integer uid;
	private Integer buid;
	private String reason;
	private Date starttime;


}
