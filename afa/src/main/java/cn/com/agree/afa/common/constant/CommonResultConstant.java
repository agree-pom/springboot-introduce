package cn.com.agree.afa.common.constant;

import lombok.AllArgsConstructor;

/**
 * TODO
 *
 * @author mx
 * @date 2021/8/4 14:56
 */
@AllArgsConstructor
public enum CommonResultConstant implements ResultConstant{
    /**
     *
     */
    SUCCESS("000000", "处理成功"),
    ERROR("111111", "处理异常"),
    NULLRESULT("M0E001", "数据为空");

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
