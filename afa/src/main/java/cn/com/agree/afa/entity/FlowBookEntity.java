package cn.com.agree.afa.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@IdClass(FlowBookPk.class)
@Entity
@Table(name = "flow_book")
public class FlowBookEntity implements Serializable {
    @Id
    @Column(name = "WORKDATE")
    private String workdate;
    @Id
    @Column(name = "AGENTSERIALNO")
    private String agentserialno;

    @Column(name = "DEALCODE")
    private String dealcode;
    @Column(name = "DEALMSG")
    private String dealmsg;

    @Column(name = "remark")
    private String remark;

}
