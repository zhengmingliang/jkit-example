package com.alianga.test.springboot3ddldemo.entity;

import com.alianga.test.springboot3ddldemo.enums.DimType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Accessors(chain = true)
@Table(name = "im_dim")
public class DimEntity extends NewBaseEntity<DimEntity> implements Serializable {

    /**
     * 唯一标识
     */
    @Id
    @Column(name = "id", length = 32)
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid.hex")
    private String id;
    /**
     * 代码 ,和valu一样 可以输入中文
     */
    @Column(name = "code_")
    private String code;
    /**
     * 状态
     */
    @Column(name = "state_", length = 1)
    private Boolean state;
    /**
     * 名称
     */
    @Column(name = "name_", length = 150)
    private String name;
    /**
     * 类型
     */
    @Column(name = "type_")
    private String type;
    /**
     * 维度值
     */
    @Column(name = "value_")
    private String value;

    /**
     * 别名,用于区分指标数据导入存放实际的数据
     */
    @Column(name = "alias", length = 150)
    private String alias;

    @Column(name = "start_date", length = 30)
    private String startDate = "19000101"; // 开始使用时间
    @Column(name = "end_date", length = 30)
    private String endDate = "22001231"; // 停止使用时间
    @Column(length = 30)
    private String col1;
    @Column(length = 30)
    private String col2;
    @Column(length = 30)
    private String col3;
    @Column(length = 30)
    private String col4;
    @Column(length = 30)
    private String col5;

    @Column(name = "dim_group_id", length = 32)
    private String dimGroupId;
    /**
     * 维度类型 0：数据维度，1.度量维度，2.维度
     */
    @Enumerated
    private DimType dimType;

    public DimEntity() {
    }

    public DimEntity(String id, String code) {
        super();
        this.id = id;
        this.code = code;
    }
}
