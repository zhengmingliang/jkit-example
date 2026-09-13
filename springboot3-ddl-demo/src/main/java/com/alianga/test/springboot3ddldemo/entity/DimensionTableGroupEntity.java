package com.alianga.test.springboot3ddldemo.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;

import java.io.Serializable;

/**
 * 实体类
 *
 * @author Administrator
 */
@Getter
@Setter
@Entity
@Accessors(chain = true)
@Table(name = "dimension_table_group")
@EqualsAndHashCode(callSuper = true)
public class DimensionTableGroupEntity extends NewBaseEntity<DimensionTableGroupEntity> implements Serializable {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid.hex")
    @Column(name = "id", length = 32)
    private String id;

    /**
     * 标题
     */
    @Column(name = "title", length = 50)
    private String title;

    /**
     * 父ID
     */
    @Column(name = "parent_id", length = 32)
    private String parentId;

    /**
     * 主题ID
     */
    @Column(name = "theme_id", length = 32)
    private String themeId;

    /**
     * 创建人id
     */
    @Column(name = "creator_id", length = 32)
    private String creatorId;

    /**
     * 修改人id
     */
    @Column(name = "mender_id", length = 32)
    private String menderId;

    public DimensionTableGroupEntity setCreatorId(String creatorId) {
        this.creatorId = creatorId;
        setCreateId(creatorId);
        return this;
    }

    public DimensionTableGroupEntity setMenderId(String menderId) {
        this.menderId = menderId;
        setModifiedId(menderId);
        return this;
    }
}
