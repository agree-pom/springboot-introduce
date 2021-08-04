package cn.com.agree.afa.entity;


import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * id pk类必须实现序列化和equals/hashCode方法
 */
@Data
public class FlowBookPk implements Serializable {

    private String workdate;
    private String agentserialno;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlowBookPk that = (FlowBookPk) o;
        return Objects.equals(workdate, that.workdate) && Objects.equals(agentserialno, that.agentserialno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(workdate, agentserialno);
    }
}
