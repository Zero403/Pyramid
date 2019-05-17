package com.mit.pyramid.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mit.pyramid.entity.FUserInvitenubers;
import org.apache.ibatis.annotations.Insert;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author Ery
 * @since 2019-05-01
 */
public interface FUserInvitenubersMapper extends BaseMapper<FUserInvitenubers> {

    @Insert("insert into f_user_invitenubers values(#{uid}, #{invitenumbers})")
    int insertKey(FUserInvitenubers invitenubers);

}