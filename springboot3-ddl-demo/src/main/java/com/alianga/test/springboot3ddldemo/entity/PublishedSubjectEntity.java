package com.alianga.test.springboot3ddldemo.entity;
/**
 * Created by 郑明亮 on 2021/11/18 11:40.
 */

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;

/**
 * <ol>
 *  2021/11/18 11:40 <br>
 *
 * </ol>
 *
 * @author 郑明亮
 * @version 1.0
 */
@Data
@Entity
@Accessors(chain = true)
@ToString(callSuper = true)
@Table(name = "published_subject", indexes = {
        @Index(name = "published_subject_caption_index", columnList = "caption"),
        @Index(name = "published_themeId_index", columnList = "theme_id")})
public class PublishedSubjectEntity extends NewBaseEntity<PublishedSubjectEntity> implements Serializable {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID) @Column(name = "id", length = 32)
    private String id;

    /**
     * 主题表的标题
     */
    @Column(name = "caption", length = 1024, nullable = false)
    private String caption;

    /**
     * 主题表的名称（当前数据存储的物理表名）
     */
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    /**
     * 描述
     */
    @Column(name = "description", length = 100)
    private String description;

    /**
     * 主题表来源类型:文件，数据库或者第三方
     * modify by 郑明亮 2022年9月6日 11:33:18 补充注释
     * 0 group为true时的默认赋值，1 数据库类型，2 文件源，
     */
    @Column(name = "type")
    private Integer type;

    /**
     * 分割符
     */
    @Column(name = "separation", length = 20)
    private String separation;

    /**
     * 是否锁定
     */
    @Column(name = "is_lock", length = 1)
    private Boolean lock;

    /**
     * 是否是实时数据
     */
    @Column(name = "is_real_time_data", length = 1)
    private Boolean realTimeData;

    /**
     * 主题表的基本结构json
     */
    @Lob
    @Column(name = "field_json")
    private String fieldJson;

    /**
     * 过滤参数
     *
     * @see com.dtsz.cm.model.vo.sqlmodel.FilterParam
     * @since 1.3.0
     */
    @Lob
    private String filterParamJson;

    /**
     * 主题集id
     */
    @Column(name = "theme_id", length = 32)
    private String themeId;

    /**
     * 父id， 默认是 root
     */
    @Column(name = "parent_id")
    private String parentId;

    /**
     * 主题表对应的同步数据来源库的物理表名
     */
    @Column(name = "table_name", length = 50)
    private String tableName;

    /**
     * 主题表来源的核心数据源id
     */
    @Column(name = "from_datasource_id", length = 64)
    private String fromDatasource;

    @Column(name = "from_sql_model_id", length = 64)
    private String fromSqlModel;

    /**
     * 存储数据库id
     */
    @Column(name = "storage_datasource_id", length = 64)
    private String storageDatasource;

    /**
     * 主题表路径
     */
    @Column(name = "group_path", length = 100)
    private String groupPath;

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

    /**
     * 是分组还是主题表   true:分组  false:主题表
     */
    @Column(name = "is_group", length = 1)
    private Boolean group;

    @Column(name = "version")
    private Integer version;

    /**
     * 模型是否已发布到服务平台
     *
     * @since 1.2.2
     */
    @Column(name = "have_publish_service")
    private Boolean havePublishService;

    /**
     * 发布到服务平台的id
     *
     * @since 1.2.2
     */
    @Column(name = "service_publish_id")
    private String servicePublishId;

    public PublishedSubjectEntity setCreatorId(String creatorId) {
        this.creatorId = creatorId;
        setCreateId(creatorId);
        return this;
    }

    public PublishedSubjectEntity setMenderId(String menderId) {
        this.menderId = menderId;
        setModifiedId(menderId);
        return this;
    }
}
