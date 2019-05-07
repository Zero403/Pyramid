package com.mit.pyramid.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mit.pyramid.common.vo.ResultVO;
import com.mit.pyramid.entity.FRealnameaut;
import com.mit.pyramid.entity.FUserBasic;

public interface FRealnameautService extends IService<FRealnameaut>{

    // 根据用户Id查询用户基本信息
    public ResultVO selectUserBasicById(int uid);

    // 根据用户Id查询用户真实信息
    public ResultVO selectRealnameByUid(int uid);

    // 新增用户真实信息
    public ResultVO insertRealname(FRealnameaut fRealnameaut);


}
