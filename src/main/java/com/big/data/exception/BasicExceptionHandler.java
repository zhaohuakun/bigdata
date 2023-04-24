package com.big.data.exception;

import com.big.data.bean.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@ControllerAdvice
public class BasicExceptionHandler {

    /**
     * description: errorHandler 处理全局异常<br>
     * @version: 1.0
     * @date: 2022/12/27 0027 下午 3:37
     * @author: William
     * @param exception   异常
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public CommonResponse errorHandler(HttpServletResponse response, Exception exception) {
        response.setStatus(400);
        exception.printStackTrace();
        return CommonResponse.builder().code(400).message(exception.getMessage()).build();
    }
}
