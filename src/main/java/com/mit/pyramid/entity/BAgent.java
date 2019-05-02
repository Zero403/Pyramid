package com.mit.pyramid.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;

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
@TableName("b_agent")
public class BAgent extends Model<BAgent> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	private String agentname;
	private String agentcompany;
	private String agentprovide;

//	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	private Date settleingtime;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAgentname() {
		return agentname;
	}

	public void setAgentname(String agentname) {
		this.agentname = agentname;
	}

	public String getAgentcompany() {
		return agentcompany;
	}

	public void setAgentcompany(String agentcompany) {
		this.agentcompany = agentcompany;
	}

	public String getAgentprovide() {
		return agentprovide;
	}

	public void setAgentprovide(String agentprovide) {
		this.agentprovide = agentprovide;
	}

	public Date getSettleingtime() {
		return settleingtime;
	}

	public void setSettleingtime(Date settleingtime) {
		this.settleingtime = settleingtime;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
