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
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author long
 * @version 1.0.0
 * @name DataAuthEntity.java 普通类
 * @date 2021年04月23日 - 16:21
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Accessors(chain = true)
@Table(name = "data_auth", indexes = {@Index(columnList = "resource_id"), @Index(columnList = "permission_id")})
public class DataAuthEntity extends NewBaseEntity<DataAuthEntity> implements Serializable {

    private static final long serialVersionUID = 6963326504252020063L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid.hex")
    @Column(name = "id", length = 64)
    private String id;

    /**
     * 资源id（主题表id、sql模型id、等）
     * @modify by zhengmingliang
     * 当type 为 10 （数据库物理表）时， 当前字段存储格式为 {dataSourceId}:{tableName}
     */
    @Column(name = "resource_id")
    private String resourceId;

    /**
     * 权限id (用户id 或角色id 或机构id)
     */
    @Column(name = "permission_id")
    private String permissionId;

    /**
     * 类型 1: 主题集 2：主题表分组 3:主题表 4: 数据源 5: 数据源分组 6: SQL模型 ， 10 数据库物理表
     * 用于描述 {@link DataAuthEntity#resourceId} 字段
     *
     * @see com.dtsz.subject.enums.DataAuthType
     */
    @Column(name = "type")
    private Integer type;

    /**
     * 使用
     */
    @Column(name = "is_use")
    private Boolean use;

    /**
     * 管理
     */
    @Column(name = "is_manager")
    private Boolean manager;

    /**
     * 列权限
     */
    @Column(name = "col_auth", length = 4000)
    private String colAuth;

    /**
     * 行权限
     */
    @Column(name = "row_auth", length = 4000)
    private String rowAuth;

    /**
     * 权限类型 role:true     user:false <br>
     * modify by 郑明亮 2022年8月12日 15:48:40 <br>
     * 将 authType 由原来的Boolean类型改为 Integer类型， 0 : user ,1 role, 2 机构 ,用于描述 {@link DataAuthEntity#resourceId} 字段
     *
     * @see com.dtsz.subject.enums.AuthType
     */
    @Column(name = "auth_type")
    private Integer authType;

    /**
     * 授权时是否授予或取消子集相同的权限
     */
    @Column(name = "is_relevant_subset")
    private Boolean relevantSubset;

    /**
     * 判断是一样
     *
     * @param target
     * @return
     */
    public Boolean isExists(DataAuthEntity target) {
        return permissionId.equalsIgnoreCase(target.getPermissionId()) &&
                resourceId.equalsIgnoreCase(target.getResourceId());
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        DataAuthEntity that = (DataAuthEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
