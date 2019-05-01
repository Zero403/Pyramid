package com.mit.pyramid.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mit.pyramid.entity.FUserBasic;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author Ery
 * @since 2019-05-01
 */
public interface FUserBasicMapper extends BaseMapper<FUserBasic> {

    @Select("select * from f_user_basic where username = #{username}")
    FUserBasic selectByName(@Param("username") String username);

}