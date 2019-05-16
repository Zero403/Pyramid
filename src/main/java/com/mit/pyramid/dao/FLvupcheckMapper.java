package com.mit.pyramid.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mit.pyramid.common.vo.CheckVO;
import com.mit.pyramid.entity.FLvupcheck;
import com.mit.pyramid.entity.FStatus;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author Ery
 * @since 2019-05-02
 */
public interface FLvupcheckMapper extends BaseMapper<FLvupcheck> {

    @Select("SELECT c.id, c.sid,b.username uname, b.phone, s.sname from f_lvupcheck c\n" +
            "inner JOIN f_user_basic b\n" +
            "ON c.lowuid = b.id\n" +
            "inner JOIN f_user_status us\n" +
            "on b.id = us.uid\n" +
            "inner JOIN f_status s\n" +
            "on s.id = us.sid\n" +
            "where c.heightuid = #{uid} and c.status = 0\n" +
            "group by uname,sid")
    List<CheckVO> checkList(@Param("uid") Integer uid);

    @Select("SELECT c.id, c.sid,b.username uname, b.phone, s.sname from f_lvupcheck c\n" +
            "inner JOIN f_user_basic b\n" +
            "ON c.heightuid = b.id\n" +
            "inner JOIN f_user_status us\n" +
            "on b.id = us.uid\n" +
            "inner JOIN f_status s\n" +
            "on s.id = us.sid\n" +
            "where c.lowuid = #{uid} and c.status = 0 order by id desc limit 1")
    CheckVO myCheck(@Param("uid") Integer uid);

    @Update("update from f_lvupcheck set status = #{status} where id = ?")
    void checkResult(int id, int status);

    @Select("SELECT c.id, c.sid,b.username uname, b.phone, s.sname from f_lvupcheck c\n" +
            "inner JOIN f_user_basic b\n" +
            "ON c.lowuid = b.id\n" +
            "inner JOIN f_user_status us\n" +
            "on b.id = us.uid\n" +
            "inner JOIN f_status s\n" +
            "on s.id = us.sid\n" +
            "where c.id = #{id}")
    CheckVO checkOne(@Param("id") Integer id);

}