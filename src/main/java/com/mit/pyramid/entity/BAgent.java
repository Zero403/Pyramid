package com.mit.pyramid.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author Chen
 * @since 2019-05-01
 */
@TableName("b_agent")
public class BAgent extends Model<BAgent> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	@TableField("agent_name")
	private String agentName;
	@TableField("agent_company")
	private String agentCompany;
	@TableField("agent_provide")
	private String agentProvide;
	@TableField("settleing_time")
	private Date settleingTime;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getAgentCompany() {
		return agentCompany;
	}

	public void setAgentCompany(String agentCompany) {
		this.agentCompany = agentCompany;
	}

	public String getAgentProvide() {
		return agentProvide;
	}

	public void setAgentProvide(String agentProvide) {
		this.agentProvide = agentProvide;
	}

	public Date getSettleingTime() {
		return settleingTime;
	}

	public void setSettleingTime(Date settleingTime) {
		this.settleingTime = settleingTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
