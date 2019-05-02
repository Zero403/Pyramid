package com.mit.pyramid.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mit.pyramid.common.vo.UserLevelDownVO;
import com.mit.pyramid.entity.BMessage;
import com.mit.pyramid.entity.BUserLevelDown;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 */
public interface BUserLevelDownMapper extends BaseMapper<BUserLevelDown> {

    @Select("select ld.lid,ld.reason,fu.username,bu.`name` from b_user_level_down ld INNER JOIN f_user_basic fu on ld. uid=fu.id INNER JOIN b_user bu on ld.buid=bu.id")
    public List<UserLevelDownVO> volist(Page<UserLevelDownVO> page);
}