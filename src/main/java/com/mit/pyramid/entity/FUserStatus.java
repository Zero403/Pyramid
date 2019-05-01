package com.mit.pyramid.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Ery
 * @since 2019-05-01
 */
@TableName("f_user_status")
public class FUserStatus extends Model<FUserStatus> {

    private static final long serialVersionUID = 1L;

	private Integer uid;
	private Integer sid;


	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	@Override
	protected Serializable pkVal() {
		return this.uid;
	}

}
