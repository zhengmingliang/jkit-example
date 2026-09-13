package com.alianga.test.springboot3ddldemo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体类
 *
 * @author Administrator
 */
@Getter
@Setter
@Entity
@Accessors(chain = true)
@Table(name = "file_source")
public class FileSourceEntity extends NewBaseEntity<FileSourceEntity> implements Serializable {

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
     * 描述
     */
    @Column(name = "description")
    private String description;

    /**
     * 文件分隔符，只针对txt文件
     */
    @Column(name = "delimiter", length = 4)
    private String delimiter;

    /**
     * 文件名
     */
    @Column(name = "file_name", length = 50)
    private String fileName;

    /**
     * 类型
     */
    @Column(name = "type", length = 10)
    private String type;

    /**
     * 文件路径
     */
    @Column(name = "file_path", length = 255)
    private String filePath;

    /**
     *
     */
    @Column(name = "source_name", length = 64)
    private String sourceName;

    /**
     * 组ID
     */
    @Column(name = "group_id", length = 32)
    private String groupId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 跟新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

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

    public FileSourceEntity setCreatorId(String creatorId) {
        this.creatorId = creatorId;
        setCreateId(creatorId);
        return this;
    }

    public FileSourceEntity setMenderId(String menderId) {
        this.menderId = menderId;
        setModifiedId(menderId);
        return this;
    }

}
