package cn.com.agree.afa.common.exception;

import cn.com.agree.afa.common.constant.ResultConstant;

/**
 * 用户安全相关异常
 *
 * @author mx
 */
public class UserSecurityException extends SystemException {
    public UserSecurityException(ResultConstant resultConstant) {
        super(resultConstant);
    }

}
