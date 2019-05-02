package com.mit.pyramid.service.impl;

import com.alibaba.fastjson.JSON;
import com.mit.pyramid.common.constsys.SystemConst;
import com.mit.pyramid.common.util.*;
import com.mit.pyramid.common.vo.LoginInfoVO;
import com.mit.pyramid.common.vo.ResultVO;
import com.mit.pyramid.common.vo.TokenVO;
import com.mit.pyramid.dao.FUserBasicMapper;
import com.mit.pyramid.dao.FUserLoginhistoryMapper;
import com.mit.pyramid.entity.FUserBasic;
import com.mit.pyramid.entity.FUserLoginhistory;
import com.mit.pyramid.service.FUserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Service
public class FUserLoginServiceImpl implements FUserLoginService {

    private JedisUtil jedisUtil = new JedisUtil("39.105.189.141",6379,"qfjava");

    @Autowired
    private FUserBasicMapper dao;

    @Autowired
    private FUserLoginhistoryMapper loginHisDao;


    @Override
    public ResultVO login(LoginInfoVO loginInfo, HttpServletResponse response) {
        String inputinfo = loginInfo.getInputinfo();
        String password = loginInfo.getPassword();
        FUserBasic user = null;

        if(CheckPhone.isMobileNO(inputinfo)) {
            user = dao.selectByPhone(inputinfo);
        } else {
            user = dao.selectByName(inputinfo);
        }


        if(user != null) {
            if(user.getPassword().equals(AESUtil.ecodes(password, SystemConst.PASS))) {
                String phone = user.getPhone();
                String token = TokenUtil.createToken(user.getId(), phone);
                jedisUtil.setStr("user:"+phone,token,1800);
                //记录当前登录用户的详细信息
                jedisUtil.setStr(token, JSON.toJSONString(user),1800);
                //3、存储到Cookie
                FUserLoginhistory fUserLoginhistory = new FUserLoginhistory();
                fUserLoginhistory.setLogintime(new Date());
                fUserLoginhistory.setUid(user.getId());
                loginHisDao.insert(fUserLoginhistory);
                return ResultUtil.exec(true,"登录成功",token);


            }
        }

        return ResultUtil.exec(false,"用户名或密码不正确",null);

    }

    @Override
    public ResultVO checkLogin(HttpServletRequest request) {
        Cookie[] arr=request.getCookies();
        String token="";
        for(Cookie c:arr){
            if(c.getName().equals("usertoken")){
                token=c.getValue();
                break;
            }
        }
        if(token.length()>0){
            //token存在
            if(jedisUtil.isExists(token)){
                //有效
                String json=jedisUtil.getStr(token);
                FUserBasic user=JSON.parseObject(json,FUserBasic.class);
                return ResultUtil.exec(true,"已经登录，且有效",user.getPhone());
            }else {
                return  ResultUtil.exec(false,"Token已经失效，请重新登录",null);
            }
        }else {
            return ResultUtil.setERROR();
        }
    }

    @Override
    public ResultVO logout(HttpServletRequest request,HttpServletResponse response) {
        Cookie[] arr=request.getCookies();
        String token="";
        for(Cookie c:arr){
            if(c.getName().equals("usertoken")){
                token=c.getValue();
                break;
            }
        }
        //删除Redis
        jedisUtil.delKey(token);
        TokenVO tokenVO=TokenUtil.parseToken(token);
        jedisUtil.delKey("user:"+tokenVO.getContent());
        //删除Cookie
        Cookie cookie=new Cookie("usertoken","");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return ResultUtil.setOK("注销成功");
    }
}
