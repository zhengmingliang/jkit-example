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
 * 元数据表实体类
 */
@Data
@Entity
@Table(name = "metadata_table")
@EqualsAndHashCode(callSuper = true)
public class MetadataTableEntity extends NewBaseEntity<MetadataTableEntity> implements Serializable {
    private static final long serialVersionUID = 1;
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID) @Column(name = "id", length = 64)
    private String id;

    /**
     * 数据源id
     */
    @Column(name = "datasource_id", length = 64)
    private String datasourceId;

    /**
     * 数据源名称
     */
    @Column(name = "datasource_name")
    private String datasourceName;

    /**
     * 表名称
     */
    @Column(name = "table_name")
    private String tableName;

    /**
     * 表中文名称
     */
    @Column(name = "table_chinese_name")
    private String tableChineseName;

    /**
     * 发布到服务平台的id
     */
    @Column(name = "service_publish_id")
    private String servicePublishId;

    /**
     * 发布到服务平台失败信息
     */
    @Column(name = "service_publish_fail_msg", length = 512)
    private String servicePublishFailMsg;

    /**
     * 元数据所在分组路径，用 json 数组存储，如 ["group1", "group2"]
     * @since 2025-06-05
     * @author zhengmingliang
     */
    @Column(name = "group_path", length = 512)
    private String groupPath;

}
