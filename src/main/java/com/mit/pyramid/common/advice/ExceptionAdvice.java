package com.mit.pyramid.common.advice;

import com.mit.pyramid.common.util.ResultUtil;
import com.mit.pyramid.common.vo.ResultVO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class ExceptionAdvice {
    @ExceptionHandler(NumberFormatException.class)
    public void numberFormatException(NumberFormatException e) {

    }

    @ExceptionHandler(Exception.class)
    public ResultVO numberFormatException(Exception e) {
        return ResultUtil.setERROR(e.getMessage());
    }
}
