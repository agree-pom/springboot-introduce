package cn.com.agree.afa.common.exception;

import cn.com.agree.afa.common.constant.ResultConstant;
import lombok.Data;

/**
 * 自定义的异常
 */
@Data
public class SystemException extends RuntimeException {

    protected final ResultConstant resultConstant;

    public SystemException(ResultConstant resultConstant) {
        super(resultConstant.message());
        this.resultConstant = resultConstant;
    }
}
