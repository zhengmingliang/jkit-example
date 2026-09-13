package com.alianga.test.springboot3ddldemo.entity;

import jakarta.persistence.GenerationType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;

/**
 * 元数据列信息
 */
@Data
@Entity
@Table(name = "metadata_column")
@EqualsAndHashCode(callSuper = true)
public class MetadataColumnEntity extends NewBaseEntity<MetadataTableEntity> implements Serializable {
    private static final long serialVersionUID = 1;
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID) @Column(name = "id", length = 64)
    private String id;

    /**
     * 元数据表id
     */
    @Column(name = "table_id", length = 64)
    private String tableId;

    /**
     * 列名称
     */
    @Column(name = "column_name")
    private String columnName;

    /**
     * 列描述
     */
    @Column(name = "column_desc", length = 3000)
    private String columnDesc;

    /**
     * 列类型
     */
    @Column(name = "column_type")
    private String columnType;

    /**
     * 列长度
     */
    @Column(name = "column_length")
    private Integer columnLength;

    /**
     * 列精度
     */
    @Column(name = "column_precision")
    private Integer columnPrecision;

    /**
     * 是否允许为空
     */
    @Column(name = "is_null", length = 1)
    private Boolean nullable;

    /**
     * 维度类型
     *
     * @see com.dtsz.subject.enums.MetadataDimensionEnum
     */
    @Column(name = "dimension_type", length = 100)
    private String dimensionType;

    /**
     * 是否为主键
     */
    @Column(name = "key_", length = 1)
    private Boolean key;

    /**
     * 注释
     */
    @Column(name = "comment_", length = 256)
    private String comment;
}
