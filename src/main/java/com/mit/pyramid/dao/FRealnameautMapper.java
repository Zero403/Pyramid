package com.mit.pyramid.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mit.pyramid.entity.BAgent;
import com.mit.pyramid.entity.FRealnameaut;
import com.mit.pyramid.entity.FStatus;
import com.mit.pyramid.entity.FUserBasic;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FRealnameautMapper extends BaseMapper<FRealnameaut> {

    // 根据用户Id查询用户基本信息
    @Select("select * from f_user_basic where id=#{uid}")
    FUserBasic selectUserBasicById(int uid);

    // 根据用户Id查询用户真实信息
    @Select("select * from f_realnameaut where uid=#{uid}")
    FRealnameaut selectFRealnameautByUid(int uid);

    // 新增用户真实信息
    @Insert("INSERT INTO f_realnameaut (uid,uname,IDnumber,frontcard,backcard,createtime) VALUES (#{uid},#{uname},#{IDnumber},#{frontcard},#{backcard},#{createtime})")
    int insertRealname(FRealnameaut fRealnameaut);

}