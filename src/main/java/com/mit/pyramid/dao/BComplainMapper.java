package com.mit.pyramid.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mit.pyramid.entity.BComplain;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author Chen
 * @since 2019-05-01
 */
public interface BComplainMapper extends BaseMapper<BComplain> {


    @Select("SELECT * FROM b_complain where status = #{status} ORDER BY createdate ASC")
    List<BComplain> listComplainByStatus(int status);


}