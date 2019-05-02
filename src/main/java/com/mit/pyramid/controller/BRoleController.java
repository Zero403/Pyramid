package com.mit.pyramid.controller;

import com.mit.pyramid.common.vo.ResultVO;
import com.mit.pyramid.service.BRoleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Ery
 * @since 2019-05-01
 */
@RestController
@RequestMapping("/mit.pyramid/bRole")
@Api(value = "角色相关", tags = "后台角色")
public class BRoleController {
    @Autowired
    private BRoleService roleService;

    @GetMapping("/rolelist.do")
    public ResultVO roleList(){
        return null;
    }


	
}
