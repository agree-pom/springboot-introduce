package cn.com.agree.afa.common.constant;

import lombok.AllArgsConstructor;

/**
 * 用户相关的结果枚举
 *
 * @author mx
 */
@AllArgsConstructor
public enum UserResultConstant implements ResultConstant {
    /**
     *
     */
    INVALID_USER("U0001", "用户名或密码错误");

    private final String code, message;
    @Override
    public String code() {
        return this.code;
    }

    @Override
    public String message() {
        return this.message;
    }
}
