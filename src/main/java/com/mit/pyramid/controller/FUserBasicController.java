package com.mit.pyramid.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mit.pyramid.common.util.ResultUtil;
import com.mit.pyramid.common.vo.RegisterVO;
import com.mit.pyramid.common.vo.ResultVO;
import com.mit.pyramid.common.vo.UserLevelDownVO;
import com.mit.pyramid.entity.FUserBasic;
import com.mit.pyramid.service.FUserBasicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Ery
 * @since 2019-05-01
 */
@RestController
@Api(value = "前台用户相关操作", tags = "操作前台用户")
public class FUserBasicController {

    @Autowired
    private FUserBasicService fUserBasicService;

    @PostMapping("user/register.do")
    @ApiOperation(value = "注册用户" , notes = "实现用户注册")
        public ResultVO save(@RequestBody @ApiParam(name = "user" ,value = "用户相关键值对") RegisterVO user){
        return fUserBasicService.userRegister(user);
    }

    @PostMapping("user/findIllegal.do")
    @ApiOperation(value="查询所有违规用户")
    public ResultVO pagelist(@RequestParam("page") @ApiParam(name = "page",value = "起始页数") int page, @RequestParam("limit") @ApiParam(name = "limit",value = "条数")int count){
        Page<FUserBasic> list = new Page<>(page, count);
        list.setRecords(fUserBasicService.userIllegal(list));

        return ResultUtil.exec(list.getRecords().size() > 0,"",list);
    }

}
