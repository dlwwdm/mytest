package com.example.demo.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    //@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseInfo handleBusinessError(HttpServletRequest request,BusinessErrorException ex) {
        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setCode(ex.getCode());
        //responseInfo.setMsg();
        responseInfo.setData(ex.getMsg());
        return responseInfo;
//        String code = ex.getCode();
//        String message = ex.getMsg();
//        return new ResponseInfo(code, message);
    }
}
