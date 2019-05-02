package com.mit.pyramid.common.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionAdvice {

    public void numberFormatException(NumberFormatException e) {

    }
}
