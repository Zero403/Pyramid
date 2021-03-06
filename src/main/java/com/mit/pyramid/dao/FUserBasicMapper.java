package com.mit.pyramid.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mit.pyramid.common.vo.BUserBasicVO;
import com.mit.pyramid.common.vo.BUserRankVO;
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
            "#{createdate},#{outsider}, #{inviterid}, #{flag},null,null)")
    @SelectKey(statement = "SELECT LAST_INSERT_ID() as id", resultType = Integer.class, keyProperty = "id",keyColumn = "id",before = false)
    Integer insertKey(FUserBasic fUserBasic);

    @Select("select * from f_user_basic where phone = #{phone}")
    FUserBasic selectByPhone(@Param("phone") String phone);

    /**
     * 分页查询违规用户
     * @param page
     * @return
     */
    @Select("select * from f_user_basic f where f.flag=2 ")
    List<FUserBasic> selectIllgal(Page<FUserBasic> page);

    @Select("select * from f_user_basic f where DAYOFYEAR(NOW()) > DAYOFYEAR(f.createdate) + #{day}")
    List<FUserBasic> selectLazy(int day);


    //后台展示代码 @author Chen
    @Select("SELECT ub.*,s.id status,s.sname FROM f_user_basic ub\n" +
            "LEFT JOIN f_user_status us\n" +
            "on ub.id=us.uid\n" +
            "INNER JOIN f_status s\n" +
            "on us.sid=s.id\n" +
            "where s.id<400")
    List<BUserBasicVO> selectAllByPage(Page<BUserBasicVO> page);


    //后台邀请榜代码 @author Chen
    @Select("SELECT ub.username,ub.id uid,us.sid,ui.invitenumbers,s.sname  FROM f_user_basic ub\n" +
            "LEFT JOIN f_user_status us\n" +
            "on ub.id = us.uid\n" +
            "INNER JOIN f_status s\n" +
            "ON us.sid = s.id\n" +
            "LEFT  JOIN f_user_invitenubers ui\n" +
            "on ub.id=ui.uid\n" +
            "WHERE us.sid<400\n" +
            "ORDER BY ui.invitenumbers DESC,us.sid desc\n" +
            "limit 50")
    List<BUserRankVO> inviteList();

    @Select("SELECT ub.username,ub.id uid,us.sid,ui.invitenumbers,s.sname  FROM f_user_basic ub\n" +
            "LEFT JOIN f_user_status us\n" +
            "on ub.id = us.uid\n" +
            "INNER JOIN f_status s\n" +
            "ON us.sid = s.id\n" +
            "LEFT  JOIN f_user_invitenubers ui\n" +
            "on ub.id=ui.uid\n" +
            "WHERE us.sid<400\n" +
            "ORDER BY us.sid desc,ui.invitenumbers DESC\n" +
            "limit 50")
    List<BUserRankVO> rankList();


}