package cn.com.agree.afa.entity;

import cn.com.agree.afa.common.enums.UserStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 用户实体
 *
 * 和数据表映射，其他如果有关联表，请创建视图与其映射类，或者使用ManyToMany、OneToMany等（较复杂，不做演示）
 *
 * 注意：所有Entity都必须有Id标注的字段，如果是联合主键需要使用PkClass，详见FlowBookEntity
 *
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "user_info")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class UserInfo implements Serializable {
    @Id
    @Basic
    @Column(name = "USER_ID")
    private Long userId;

    @Basic
    @Column(name = "USER_CODE", unique = true, nullable = false)
    private String userCode;

    @Basic
    @Column(name = "USER_NAME", unique = true, nullable = false)
    private String userName;

    @Basic
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Basic
    @Enumerated(value = EnumType.STRING)
    @Column(name = "USER_STATUS")
    private UserStatus userStatus;

    @Basic
    @Column(name = "LOGIN_CNT")
    private int loginCnt = 0;

}
