package com.alianga.test.springboot3ddldemo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;

import java.io.Serializable;

/**
 * @description 数据变更日志
 */
@Getter
@Setter
@Entity
@Accessors(chain = true)
@Table(name = "replace_table_log", indexes = {
        @Index(name = "targetId_index", columnList = "target_id"),
        @Index(name = "datasourceId_index", columnList = "datasourceId")
})
public class ReplaceTableLogEntity extends NewBaseEntity<ReplaceTableLogEntity> implements Serializable {

    private static final long serialVersionUID = 696332604252020063L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid.hex")
    @Column(name = "id", length = 64)
    private String id;

    /**
     * 操作的表的id
     */
    @Column(name = "target_id", length = 64)
    private String targetId;

    /**
     * 类型 0: sql模型 1：主题表 2:维度表
     */
    @Column(name = "types")
    private Integer types;

    /**
     * 数据源id
     */
    @Column(name = "datasourceId", length = 64)
    private String datasourceId;

    /**
     * 旧值
     */
    @Column(name = "old_data", length = 1000)
    private String oldData;
    /**
     * 新值
     */
    @Column(name = "new_data", length = 1000)
    private String newData;

}
