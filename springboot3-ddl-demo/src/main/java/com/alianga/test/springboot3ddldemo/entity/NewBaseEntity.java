/*
 * Created by 郑明亮 on 2020/10/14 17:27.
 */

//

package com.alianga.test.springboot3ddldemo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;

import java.io.Serializable;
import java.util.Date;

/**
 * <ol>
 *
 * </ol>
 * <p>
 * 替换掉common-db里的BaseEntityListener
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Accessors(chain = true)
@MappedSuperclass
@Filter(condition = "application_id=:applicationId", name = "applicationFilter")
public class NewBaseEntity<T> implements Serializable {

    /**
     * 是否可见（用于逻辑删除）
     */
    @JsonIgnore
    @Column(name = "can_view", length = 1)
    public Boolean canView = Boolean.TRUE;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 租户ID
     */
    @Column(name = "tenant_id")
    private String tenantId;

    /**
     * 租户code
     */
    @Column(name = "tenant_code")
    private String tenantCode;

    /**
     * 修该人ID
     */
    @Column(name = "modified_id", length = 32)
    private String modifiedId;

    /**
     * 创建人ID
     */
    @Column(name = "create_id", length = 32)
    private String createId;

    @Column(name = "readonly_col")
//    @ColumnDefault("0")
    private Boolean readonly = Boolean.FALSE;

    /**
     * 应用ID
     */
    @Column(name = "application_id", length = 32)
    private String applicationId;

    public T setCanView(Boolean canView) {
        this.canView = canView;
        return (T) this;
    }

    public T setCreateTime(Date createTime) {
        this.createTime = createTime;
        return (T) this;
    }

    public T setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return (T) this;
    }

    public T setTenantId(String tenantId) {
        this.tenantId = tenantId;
        return (T) this;
    }

    public T setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
        return (T) this;
    }

    public T setModifiedId(String modifiedId) {
        this.modifiedId = modifiedId;
        return (T) this;
    }

    public T setCreateId(String createId) {
        this.createId = createId;
        return (T) this;
    }

    public T setReadonly(Boolean readonly) {
        this.readonly = readonly;
        return (T) this;
    }

    public Boolean getReadonly() {
        return readonly != null && readonly;
    }

    public T setApplicationId(String applicationId) {
        this.applicationId = applicationId;
        return (T) this;
    }

}
