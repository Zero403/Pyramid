package com.mit.pyramid.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author Chen
 * @since 2019-05-02
 */
@TableName("f_user_true")
@Data
public class FUserTrue extends Model<FUserTrue> {

	private static final long serialVersionUID = 1L;

	@TableId(value="tid", type= IdType.AUTO)
	private Integer tid;
	private String tname;
	private String tnum;
	private String tpicon;
	private String tpicoff;
	private Integer uid;

}
