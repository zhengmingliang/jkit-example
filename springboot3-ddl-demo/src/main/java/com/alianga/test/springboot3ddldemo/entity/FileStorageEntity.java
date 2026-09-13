package com.alianga.test.springboot3ddldemo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
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
 * @name FileStorageEntity.java 普通类
 * @description 文件源存储表
 * @date 2021年08月18日 - 16:56
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Accessors(chain = true)
@Table(name = "file_storage")
public class FileStorageEntity extends NewBaseEntity<FileStorageEntity> implements Serializable {

    private static final long serialVersionUID = -601154726526664190L;

    @Id
    @Column(name = "id", length = 32)
    @GeneratedValue(generator = "system-uuid")  private String id;

    /**
     * 描述
     */
    @Column(name = "source_desc")
    private String desc;

    /**
     * 文件源名字
     */
    @Column(name = "source_name")
    private String fileSourceName;

    /** 防止更新的时候修改文件源名字导致无法找到对应的表信息 */
//    @Column(name = "source_name_real")
//    private String sourceNameReal;

    /**
     * 真是文件名字  对应的磁盘上文件真是名字
     */
    @Column(name = "real_name")
    private String realName;

    /**
     * 文件源类型
     */
    @Column(name = "file_type")
    private String type;

    /**
     * 文件分隔符
     */
    @Column(name = "delimiter")
    private Integer delimiter;

    /**
     * 文件源对应的属性信息 这里保存json字符串
     */
    @Lob
    @Column(name = "fields")
    private String fields;

    /**
     * 用户信息
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 开始读取的位置
     */
    @Column(name = "read_start")
    private Integer start;

    /**
     * 同步完成时间
     */
//    @Column(name = "create_time")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    private Date createTime;

    /**
     * 同步开始时间（createTime和startTime不一定相等）
     */

}
