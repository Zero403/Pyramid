package com.mit.pyramid.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mit.pyramid.common.vo.BUserBasicVO;
import com.mit.pyramid.common.vo.BUserRankVO;
import com.mit.pyramid.common.vo.RegisterVO;
import com.mit.pyramid.common.vo.ResultVO;
import com.mit.pyramid.entity.FUserBasic;

import java.util.List;

public interface FUserBasicService extends IService<FUserBasic> {

    ResultVO userRegister(RegisterVO user);

    List<FUserBasic> userIllegal(Page<FUserBasic> page);



    //后台展示会员列表代码 @author Chen
    List<BUserBasicVO> listByPage(Page<BUserBasicVO> page);

    //后台插入一条会员记录 @auther Chen
    int insertUser(FUserBasic userBasic,int status);

//    //后台批量插入会员记录 @auther Chen
//    int addBatch(List<BUserBasicVO> list);
    // 懒惰用户查询
    List<FUserBasic> userLazy(int days);


    //后台邀请排行榜
    List<BUserRankVO> inviteList();

    //后台等级排行榜
    List<BUserRankVO> rankList();
}
