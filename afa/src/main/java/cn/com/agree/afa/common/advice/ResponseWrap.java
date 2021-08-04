package cn.com.agree.afa.common.advice;

import cn.com.agree.afa.common.constant.CommonResultConstant;
import cn.com.agree.afa.common.result.ResultMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;

/**
 * 对结果进行封装
 *
 * @author mx
 * @date 2021/8/4 14:59
 */
@Slf4j
@ControllerAdvice
public class ResponseWrap implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        log.info("返回结果 -> {}", body);
        if (body == null) {
            return ResultMessage.wrap(CommonResultConstant.NULLRESULT);
        }

        String requestPath = serverHttpRequest.getURI().getPath();
        log.info("body的数据类型 -> {}, requestPath -> {}", body.getClass().getName(), requestPath);
        if (body instanceof ResultMessage) {
            return body;
        }

        serverHttpResponse.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        // filter中出现异常 path变成error
        if (Objects.equals(requestPath, "/error")) {
            return ResultMessage.error();
        }

        // 注意：body为String类型的需要return一个string类型
        if (body instanceof String) {
            return ResultMessage.success(body).toString();
        }

        return ResultMessage.success(body);
    }
}
