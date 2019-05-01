package com.mit.pyramid.controller;

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
import org.springframework.web.bind.annotation.PostMapping;
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
@Api(value = "投诉相关操作",tags = "操作投诉")
public class BComplainController {

    @Autowired
    private BComplainService bComplainService;

    @ApiOperation(value = "投诉列表",notes = "实现投诉的分页展示")
    @PostMapping("complain/listbypage.do")
    public ResultVO listByPage(@RequestParam("page") @ApiParam(name = "page",value = "页码") int page, @RequestParam("limit") @ApiParam(name = "limit",value = "页数")int limit){


        Page<BComplain> complainPage = new Page<>(page, limit);
        IPage<BComplain> iPage = bComplainService.page(complainPage);


        return ResultUtil.exec(true, String.valueOf(complainPage.getTotal()),iPage.getRecords());
    }
	
}
