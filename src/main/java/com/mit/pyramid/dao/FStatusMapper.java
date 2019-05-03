package com.mit.pyramid.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mit.pyramid.entity.FStatus;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author Ery
 * @since 2019-05-01
 */
public interface FStatusMapper extends BaseMapper<FStatus> {

    @Select("select * from f_status where id = 101")
    List<FStatus> lv1List();

    @Select("select * from f_status where id between #{nowid} and #{highid}")
    List<FStatus> lvStarList(@Param("nowid") Integer nowid,@Param("highid") Integer highid);

}