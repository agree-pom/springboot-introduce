package cn.com.agree.afa.common.result;

import cn.com.agree.afa.common.constant.CommonResultConstant;
import cn.com.agree.afa.common.constant.ResultConstant;
import cn.com.agree.afa.common.exception.SystemException;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * 返回结果封装
 *
 * @author mx
 * @date 2021/8/4 14:50
 */
@Slf4j
@Data
@Accessors(chain = true)
public class ResultMessage implements Serializable {
    private String code;
    private String message;
    private Map<Object, Object> content = new HashMap<>();

    public static ResultMessage wrap(ResultConstant resultConstant, Object content) {
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setMessage(resultConstant.message())
                .setCode(resultConstant.code());
        resultMessage.content.put("data", content);

        return resultMessage;
    }

    public static ResultMessage success(Object content) {
        return wrap(CommonResultConstant.SUCCESS, content);
    }

    public static ResultMessage error() {
        return wrap(CommonResultConstant.ERROR);
    }

    public static ResultMessage wrap(ResultConstant resultConstant) {
        return wrap(resultConstant, null);
    }

    public static ResultMessage build(Supplier<? extends Throwable> supplier) {
        Throwable throwable = supplier.get();
        if (throwable instanceof SystemException) {
            SystemException systemException = (SystemException) throwable;
            ResultConstant resultConstant = systemException.getResultConstant();
            return wrap(resultConstant);
        }

        return new ResultMessage().setMessage(throwable.getMessage()).setCode("111111");
    }
}
