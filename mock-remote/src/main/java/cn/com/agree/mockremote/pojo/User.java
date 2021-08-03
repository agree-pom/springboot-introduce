package cn.com.agree.mockremote.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * TODO
 *
 * @author mx
 * @date 2021/8/3 10:54
 */
@Data
@Accessors(chain = true)
public class User implements Serializable {
    private String userName;
    private String userCode;
    private int age;
    private String address;
}
