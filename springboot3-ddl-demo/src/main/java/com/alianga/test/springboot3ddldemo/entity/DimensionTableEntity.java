package com.alianga.test.springboot3ddldemo.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
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
@Table(name = "dimension_table")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DimensionTableEntity extends NewBaseEntity<DimensionTableEntity> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", length = 32)
    private String id;

    /**
     * 维表标题
     */
    @Column(name = "title", length = 1024)
    // 前端限制长度100 调整为1024以应对四态迁移中增加前缀的情况
    private String title;

    /**
     * 维表名称
     */
    @Column(name = "name", length = 100)
    private String name;

    /**
     * 维表描述
     */
    @Column(name = "description")
    private String description;

    /**
     * 来源的连接池名称,StorageDatasourceEntity对应的name是唯一的,新加的字段
     */
    @Column(name = "pool_name", length = 50)
    private String poolName;

    /**
     * 文件源和数据源类型
     */
    @Column(name = "source_type", length = 1)
    private Integer sourceType;

    /**
     * 文件源对应的分隔符
     */
    @Column(name = "separation", length = 10)
    private String separation;

    /**
     * 来源数据表名称
     */
    @Column(name = "table_name", length = 100)
    private String tableName;

    /**
     * 是否锁定数据表
     */
    @Column(name = "is_lock", length = 1)
    private Integer isLock;

    /**
     * 数据期类型
     */
    @Column(name = "time_type", length = 50)
    private String timeType;

    /**
     * 开始时间
     */
    @Column(name = "start_time", length = 4)
    private String startTime;

    /**
     * 结束时间
     */
    @Column(name = "end_time", length = 4)
    private String endTime;

    /**
     * 数据期标识字段
     */
    @Column(name = "time_field", length = 50)
    private String timeField;

    /**
     * id字段(用于标识用来进行拆分的字段或者螺旋维的id字段) ，当dimenType 为3 或者treeType 为1 的时候，该字段不能为空
     */
    @Column(name = "id_field", length = 50)
    private String idField;
    /**
     * 文本字段，用于标识id字段对应的中文是映射字段
     */
    @Column(name = "text_field", length = 50)
    private String textField;

    /**
     * 父节点字段，当dimenType 为3(螺旋维表)时，该字段不能为空
     */
    @Column(name = "parent_field", length = 50)
    private String parentField;

    /**
     * 根节点值，当dimenType 为3(螺旋维表)时，该字段不能为空
     */
    @Column(name = "root_value", length = 50)
    private String rootValue;

    /**
     * 每段分割数配置信息，分割符用“-”隔开，如1002011 段分割数配置：3-2-2 ，则分割后 得到 100 20 11
     */
    @Column(name = "segment_split_numbers", length = 50)
    private String segmentSplitNumbers;

    /**
     * 分段名，用来对应拆分的每段数据对应的中文描述 ，多个用 “-” 隔开，例如：年-月-日
     */
    @Column(name = "segment_split_names", length = 50)
    private String segmentSplitNames;

    /**
     * 来源类型 1:普通维表   2:数据维表 、3 螺旋维表
     */
    @Column(name = "dimen_type")
    private Integer dimenType;

    /**
     * 时间格式
     */
    @Column(name = "date_format")
    private String dateFormat;

    /**
     * 属性json
     */
    @Lob
    @Column(name = "property_json")
    private String propertyJson;

    /**
     * 钻取路径Json格式
     */
//    @Lob
    @Column(name = "drill_json", length = 1000)
    private String drillJson;

    /**
     * 0为无层次；1为分段；2为钻取
     */
    @Column(name = "tree_type", length = 2)
    private Integer treeType;

    /**
     * 树形具体内容
     */
    @Column(name = "tree_type_detail", length = 255)
    private String treeTypeDetail;

    /**
     * 关联字段
     */
    @Column(name = "associated_field", length = 50)
    private String associatedField;

    /**
     * 关联字段类型，associatedField为空时为空（新增，用于判断是否需要分词，并防止多次查库）
     */
    @Column(name = "associated_field_type", length = 10)
    private String associatedFieldType;

    /**
     * 是否缓冲到内存
     */
    @Column(name = "is_buffer_ram", length = 1)
    private Integer isBufferRam;

    /**
     * 有效时间
     */
    @Column(name = "effective_time")
    private Long effectiveTime;

    /**
     * 是否为实时数据
     */
    @Column(name = "real_time_data", length = 1)
    private Boolean realTimeData;

    /**
     * 来源数据库id
     */
    @Column(name = "from_source_id", length = 32)
    private String fromSourceId;

    /**
     * 保存数据库id
     */
    @Column(name = "save_source_id", length = 64)
    private String saveSourceId;

    /**
     * 所属分组id
     */
    @Column(name = "group_id", length = 32)
    private String groupId;

    /**
     * 所属主题集id
     */
    @Column(name = "theme_id", length = 32)
    private String themeId;

    /**
     * 创建人id
     */
    @Column(name = "creatorId", length = 32)
    private String creatorId;

    /**
     * 修改人id
     */
    @Column(name = "menderId", length = 32)
    private String menderId;

    /**
     * 是分组还是主题表   true:分组  false:维表
     */
    @Column(name = "is_group", length = 1)
    private Boolean group;

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

    public DimensionTableEntity setCreatorId(String creatorId) {
        this.creatorId = creatorId;
        setCreateId(creatorId);
        return this;
    }

    public DimensionTableEntity setMenderId(String menderId) {
        this.menderId = menderId;
        setModifiedId(menderId);
        return this;
    }

}
