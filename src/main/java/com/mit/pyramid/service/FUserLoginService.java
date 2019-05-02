package com.mit.pyramid.service;

import com.mit.pyramid.common.vo.LoginInfoVO;
import com.mit.pyramid.common.vo.ResultVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface FUserLoginService {

    ResultVO login(LoginInfoVO loginInfo,HttpServletResponse response);

    ResultVO checkLogin(HttpServletRequest request);

    ResultVO logout(HttpServletRequest request, HttpServletResponse response);

}
