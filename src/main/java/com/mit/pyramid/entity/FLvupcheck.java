package com.mit.pyramid.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Ery
 * @since 2019-05-02
 */
@TableName("f_lvupcheck")
public class FLvupcheck extends Model<FLvupcheck> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	private Integer lowuid;
	private Integer heightuid;
	private Integer status;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLowuid() {
		return lowuid;
	}

	public void setLowuid(Integer lowuid) {
		this.lowuid = lowuid;
	}

	public Integer getHeightuid() {
		return heightuid;
	}

	public void setHeightuid(Integer heightuid) {
		this.heightuid = heightuid;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
