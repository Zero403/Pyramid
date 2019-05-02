package com.mit.pyramid.entity;

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
 * @since 2019-05-01
 */
@TableName("f_user_invitenubers")
public class FUserInvitenubers extends Model<FUserInvitenubers> {

    private static final long serialVersionUID = 1L;

    @TableId("uid")
	private Integer uid;
	private Integer invitenumbers;


	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getInvitenumbers() {
		return invitenumbers;
	}

	public void setInvitenumbers(Integer invitenumbers) {
		this.invitenumbers = invitenumbers;
	}

	@Override
	protected Serializable pkVal() {
		return this.uid;
	}

}
