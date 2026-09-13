package com.alianga.test.springboot3ddldemo.entity;

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
@Table(name = "file_source_group")
public class FileSourceGroupEntity extends NewBaseEntity<FileSourceGroupEntity> implements Serializable {

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
    @Column(name = "title", length = 100)
    private String title;

    /**
     * 父ID
     */
    @Column(name = "parent_id", length = 32)
    private String parentId;

    /**
     * 描述
     */
    @Column(name = "description")
    private String description;

    /**
     * 创建人ID
     */
    @Column(name = "creator_id", length = 32)
    private String creatorId;

    /**
     * 修改人ID
     */
    @Column(name = "mender_id", length = 32)
    private String menderId;

    public FileSourceGroupEntity setCreatorId(String creatorId) {
        this.creatorId = creatorId;
        setCreateId(creatorId);
        return this;
    }

    public FileSourceGroupEntity setMenderId(String menderId) {
        this.menderId = menderId;
        setModifiedId(menderId);
        return this;
    }
}
