package com.mit.pyramid.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mit.pyramid.common.util.ResultUtil;
import com.mit.pyramid.common.vo.ResultVO;
import com.mit.pyramid.entity.BComplain;
import com.mit.pyramid.service.BComplainService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Chen
 * @since 2019-05-01
 */
@RestController
@Api(value = "投诉相关操作",tags = "投诉管理")
public class BComplainController {

    @Autowired
    private BComplainService bComplainService;

    @ApiOperation(value = "投诉列表",notes = "实现投诉的按状态分页展示(按时间升序)，status 投诉状态 0表示未审核 1 表示审核 点击审核按钮并对举报人进行处理（降级处理），2表示审核过，投诉理由不足驳回，1,2都要发消息反馈给投诉者")
    @GetMapping("complain/listbypage.do")
    public ResultVO listByPage(@RequestParam("page") @ApiParam(name = "page",value = "页码") int page, @RequestParam("limit") @ApiParam(name = "limit",value = "每页个数")int limit, @RequestParam("status") @ApiParam(name = "status",value = "投诉的状态")int status){

        Page<BComplain> complainPage = new Page<>(page, limit);
        IPage<BComplain> iPage = bComplainService.page(complainPage,new QueryWrapper<BComplain>().eq("status", status).orderByAsc("createdate"));

        return ResultUtil.exec(true, String.valueOf(complainPage.getTotal()),iPage.getRecords());
    }
	
}
