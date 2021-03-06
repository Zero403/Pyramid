package com.mit.pyramid.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mit.pyramid.entity.FUserStatus;
import org.apache.ibatis.annotations.Insert;
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
public interface FUserStatusMapper extends BaseMapper<FUserStatus> {

    @Select("select count(*) from f_user_status where sid = 105")
    int lv5num();
    @Select("select count(*) from f_user_status where sid = 109")
    int lv9num();
    @Select("select count(*) from f_user_status where sid = 113")
    int lv13num();

    @Insert("insert into f_user_status values(#{uid}, #{sid})")
    int insertKey(FUserStatus fUserStatus);

    /**
     * 随机选一个特殊等级的用户
     * @param sid
     * @return
     */
    @Select("select * from f_user_status where sid = #{sid} order by rand() limit 1 ")
    FUserStatus rand1SpecialUser(@Param("sid") Integer sid);

    @Select("select s.sname from f_user_status u inner join f_status s on u.sid = s.id where u.uid = #{uid}")
    String getStatusName(@Param("uid") Integer uid);
}