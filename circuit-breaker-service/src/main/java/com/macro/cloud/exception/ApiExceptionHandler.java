package com.macro.cloud.exception;

import com.macro.cloud.domain.CommonResult;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.TimeoutException;

/**
 * @auther macrozheng
 * @description 自定义API异常处理类
 * @date 2023/12/12
 * @github https://github.com/macrozheng
 */
@ControllerAdvice
public class ApiExceptionHandler {

    private Logger LOGGER = LoggerFactory.getLogger(ApiExceptionHandler.class);

    @ExceptionHandler({CallNotPermittedException.class})
    @ResponseBody
    public CommonResult handleCallNotPermittedException(CallNotPermittedException e) {
        LOGGER.info("handleCallNotPermittedException:{}",e.getMessage());
        return new CommonResult(null,e.getMessage(),503);
    }

    @ExceptionHandler({ RequestNotPermitted.class })
    @ResponseBody
    public CommonResult handleRequestNotPermitted(RequestNotPermitted e) {
        LOGGER.info("handleRequestNotPermitted:{}",e.getMessage());
        return new CommonResult(null,e.getMessage(),429);
    }

    @ExceptionHandler({TimeoutException.class})
    @ResponseBody
    public CommonResult handleTimeoutException(TimeoutException e) {
        LOGGER.info("handleTimeoutException:{}",e.getMessage());
        return new CommonResult(null,e.getMessage(),408);
    }
}