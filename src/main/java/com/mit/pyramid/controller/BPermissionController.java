package com.mit.pyramid.controller;

import com.mit.pyramid.common.vo.ResultVO;
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
@RequestMapping("/permission")
public class BPermissionController {

    @GetMapping("/menu")
    public ResultVO menu(){
        return null;
    }
}
