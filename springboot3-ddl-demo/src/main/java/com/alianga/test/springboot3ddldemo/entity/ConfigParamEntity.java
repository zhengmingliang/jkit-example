package com.alianga.test.springboot3ddldemo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;


import java.io.Serializable;
import java.util.Date;

/**
 * @author huzeyu
 * @version 1.0.0
 * @ClassName ConfigParamEntity.java
 * @Description 配置信息表
 * @date 2020-02-25
 */
@Setter
@Getter
@Entity
@Accessors(chain = true)
@Table(name = "config_param")
public class ConfigParamEntity implements Serializable {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid.hex")
    @Column(name = "id")
    private String id;

    /**
     * 配置码
     */
    @Column(name = "config_code1")
    private String configCode;

    /**
     * 父配置码
     */
    @Column(name = "parent_config_code")
    private String parentConfigCode;

    /**
     * 配置名称
     */
    @Column(name = "config_name")
    private String configName;

    /**
     * 配置值
     */
    @Column(name = "config_value")
    private String configValue;

    /**
     * 配置备注
     */
    @Column(name = "config_comment")
    private String configComment;

    /**
     * 创建用户id
     */
    @Column(name = "create_user_id")
    private String createUserId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新用户id
     */
    @Column(name = "update_user_id")
    private String updateUserId;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 是否有效：1有效，0无效
     */
    @Column(name = "yn")
    private Integer yn;

    /**
     * 驱动版本
     */
    @Column(name = "driver_name")
    private String driverName;
    /**
     * 数据源类型 或参数类型
     */
    @Column(name = "type")
    private String type;
}
