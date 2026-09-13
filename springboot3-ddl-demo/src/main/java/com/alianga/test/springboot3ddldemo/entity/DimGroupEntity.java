package com.alianga.test.springboot3ddldemo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;


import java.io.Serializable;

@Getter
@Setter
@Entity
@Accessors(chain = true)
@Table(name = "dim_group")
@EqualsAndHashCode(callSuper = true)
public class DimGroupEntity extends NewBaseEntity<DimGroupEntity> implements Serializable {

    private static final long serialVersionUID = -7587802548291602490L;

    /**
     * 唯一标识
     */
    @Id
    @Column(name = "id", length = 32)
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid.hex")
    private String id;
    /**
     * 代码
     */
    @Column(name = "code_", length = 60)
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

    @Column(name = "start_date", length = 30)
    private String startDate = "19000101"; // 开始使用时间
    @Column(name = "end_date", length = 30)
    private String endDate = "22001231"; // 停止使用时间

    @Column(name = "col1", length = 30)
    private String col1;
    @Column(name = "col2", length = 30)
    private String col2;

    @Column(name = "PARENT_ID", length = 32)
    private String parentId;

    @Column(name = "path_")
    private String path;

    @Column(name = "parent_code", length = 60)
    private String parentCode;

    @Column(name = "leaf_", length = 1)
    private Boolean leaf;

    public DimGroupEntity() {
        //
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
