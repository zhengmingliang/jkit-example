package com.alianga.test.springboot3ddldemo.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 墨龙吟
 * @version 1.0.0
 * @ClassName DeleteDateEntity.java
 * @Email 2354713722@qq.com
 * @Description 存放删除数据
 * @createTime 2019年11月12日 - 16:41
 */
@Setter
@Getter
@Entity
@Accessors(chain = true)
@Table(name = "delete_date")
@EqualsAndHashCode(callSuper = true)
public class DeleteDateEntity extends NewBaseEntity<DeleteDateEntity> implements Serializable {

    private static final long serialVersionUID = 1297064482683806184L;

    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid.hex")
    @Column(name = "id", length = 32)
    private String id;

    /**
     * 被删除的表的ID
     */
    @Column(name = "del_id", length = 32)
    private String delId;

    /**
     * 主题集ID
     */
    @Column(name = "theme_id", length = 32)
    private String themeId;

    /**
     * 标题
     */
    @Column(name = "title", length = 150)
    private String title;

    /**
     * 类型：true(主题表)  false(维表)
     */
    @Column(name = "is_type", length = 1)
    private Boolean type;

    /**
     * 分组： true(组)  false(表)
     */
    @Column(name = "is_group", length = 1)
    private Boolean group;

    /**
     * 父ID
     */
    @Column(name = "group_id", length = 32)
    private String groupId;

    /**
     * 操作人
     */
    @Column(name = "user_name", length = 150)
    private String userName;

    /**
     * 用户ID
     */
    @Column(name = "user_id", length = 32)
    private String userId;

    /**
     * 删除时间
     */
    @Column(name = "delete_time")
    private Date deleteTime;

}
