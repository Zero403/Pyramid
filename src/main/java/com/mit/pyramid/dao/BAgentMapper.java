package com.mit.pyramid.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mit.pyramid.entity.BAgent;
import org.apache.ibatis.annotations.Insert;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author Chen
 * @since 2019-05-01
 */
public interface BAgentMapper extends BaseMapper<BAgent> {

    @Insert("INSERT INTO b_agent (agentname,agentcompany,agentphone,settleingtime) VALUES (#{agentname},#{agentcompany},#{agentphone},#{settleingtime})")
    int insertAgent(BAgent agent);

}