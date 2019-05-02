package com.mit.pyramid.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mit.pyramid.entity.FUserBasic;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import java.util.List;

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

    @Insert("insert into f_user_basic values(null,#{username}, #{password}, #{phone}," +
            "#{createdate}, #{inviterid}, #{flag},null,null)")
    @SelectKey(statement = "SELECT LAST_INSERT_ID() as id", resultType = Integer.class, keyProperty = "id",keyColumn = "id",before = false)
    Integer insertKey(FUserBasic fUserBasic);

    @Select("select * from f_user_basic where phone = #{phone}")
    FUserBasic selectByPhone(@Param("phone") String phone);

    @Select("select * from f_user_basic f where f.flag=2 ")
    List<FUserBasic> selectIllgal(Page<FUserBasic> page);
}