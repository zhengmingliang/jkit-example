package com.alianga.test.springboot3ddldemo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Accessors(chain = true)
@Table(name = "des_rule_mapping", indexes = {@Index(columnList = "resource_id"), @Index(columnList = "rule_id")})
public class DesensitizationRuleMappingEntity {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid.hex")
    @Column(name = "id", length = 64)
    private String id;

    /**
     * 资源id（主题表id、sql模型id、数据源id等）
     */
    @Column(name = "resource_id", length = 64)
    private String resourceId;

    /**
     * 脱敏规则id
     */
    @Column(name = "rule_id", length = 64)
    private String ruleId;

    /**
     * 类型 1: 主题集 2：主题表分组 3:主题表 4: 数据源 5: 数据源分组 6: SQL模型 ，用于描述 {@link DesensitizationRuleMappingEntity#resourceId} 字段
     *
     * @see com.dtsz.subject.enums.DataAuthType
     */
    @Column(name = "type")
    private Integer type;

    /**
     * 要进行脱敏的字段名
     */
    @Column(name = "column_name", length = 1000)
    private String columnName;

    /**
     * 脱敏字段类型
     */
    @Column(name = "column_type", length = 32)
    private String columnType;

}
