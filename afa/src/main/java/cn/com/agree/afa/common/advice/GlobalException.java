package cn.com.agree.afa.common.advice;

import cn.com.agree.afa.common.result.ResultMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常捕获
 *
 * @author mx
 * @date 2021/8/4 14:48
 */
@Slf4j
@RestControllerAdvice
public class GlobalException {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResultMessage errorHandler(Exception ex) {
        log.error("捕获到全局Exception -> {}", ex.getMessage());
        ex.printStackTrace();
        return ResultMessage.build(() -> ex.getCause() == null ? ex : ex.getCause());
    }
}
