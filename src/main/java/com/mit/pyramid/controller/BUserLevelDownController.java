package com.mit.pyramid.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mit.pyramid.common.util.ResultUtil;
import com.mit.pyramid.common.vo.ResultVO;
import com.mit.pyramid.common.vo.UserLevelDownVO;
import com.mit.pyramid.entity.BMessage;
import com.mit.pyramid.entity.BUserLevelDown;
import com.mit.pyramid.entity.FUserBasic;
import com.mit.pyramid.service.BMessageService;
import com.mit.pyramid.service.BUserLevelDownService;
import com.mit.pyramid.service.FUserBasicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 */
@RestController
@RequestMapping("/userLevel")
@Api(value = "降级消息相关", tags = "降级消息")
public class BUserLevelDownController {
    @Autowired
    private BUserLevelDownService bUserLevelDownService;

    @Autowired
    private FUserBasicService fUserBasicService;


    @PostMapping("pagelist.do")
    @ApiOperation(value = "分页展示所有降级消息")
    public ResultVO pagelist(@RequestParam("page") @ApiParam(name = "page",value = "起始页数") int page, @RequestParam("limit") @ApiParam(name = "limit",value = "条数")int count){
        Page<UserLevelDownVO> list = new Page<>(page, count);
        list.setRecords(bUserLevelDownService.findAll(list));

        return ResultUtil.exec(list.getRecords().size() > 0,"",list);
    }
}
