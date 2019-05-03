package com.mit.pyramid.controller;

import com.mit.pyramid.common.vo.ResultVO;
import com.mit.pyramid.service.BProportionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Ery
 * @Date: 2019/5/3 14:01
 * @Version null
 */
@RestController
@RequestMapping("/proportion")
@Api(value = "比例相关", tags = "比例修改")
public class BPropertionController {

    @Autowired
    private BProportionService proportionService;

    @PutMapping("/updateproportion.do")
    @ApiOperation(value = "修改比例", notes = "修改各个特殊星级所占比例 都不能为空")
    public ResultVO update(Integer five, Integer nine, Integer thirteen){
        return proportionService.updateProportion(five, nine, thirteen);
    }
}
