package com.alianga.test.springboot3ddldemo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;

import java.io.Serializable;

/**
 * 元数据关联关系
 */
@Data
@Entity
@Table(name = "metadata_relation")
@EqualsAndHashCode(callSuper = true)
public class MetadataRelationEntity extends NewBaseEntity<MetadataTableEntity> implements Serializable {
    private static final long serialVersionUID = 1;
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID) @Column(name = "id", length = 64)
    private String id;

    /**
     * 主表id
     */
    @Column(name = "main_id", length = 64)
    private String mainId;

    /**
     * 主表名
     */
    @Column(name = "main_name")
    private String mainName;

    /**
     * 从表ID
     */
    @Column(name = "form_id", length = 64)
    private String formId;

    /**
     * 从表名
     */
    @Column(name = "form_name")
    private String formName;

    /**
     * 连接方式
     * @see com.dtsz.subject.enums.LinkedWayEnum
     */
    @Column(name = "linked_way", length = 50)
    private String linkedWay;

    /**
     * 关联关系
     */
    @Lob
    @Column(name = "relation")
    private String relation;
}
