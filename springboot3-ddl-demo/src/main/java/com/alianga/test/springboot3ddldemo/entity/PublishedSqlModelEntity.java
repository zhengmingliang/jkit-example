package com.alianga.test.springboot3ddldemo.entity;
/**
 * Created by 郑明亮 on 2021/11/18 11:40.
 */

import io.micrometer.common.util.StringUtils;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Lob;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.Map;

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
@Table(name = "published_sql_model", indexes = {@Index(columnList = "parent_id"),
        @Index(columnList = "datasource_id,model_code,can_view", unique = false)})
public class PublishedSqlModelEntity extends NewBaseEntity<PublishedSqlModelEntity> implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "id", length = 32)
    @GeneratedValue(strategy = GenerationType.UUID) private String id;

    @Column(name = "parent_id", length = 32)
    private String parentId;

    /**
     * 关联的数据源id
     */
    @Column(name = "datasource_id")
    private String datasourceId;

    /**
     * 当前的sql语句
     * 存放 动态模型的 sql条件（sqlCondition）
     */
    @Column(name = "sql_")
    @Lob
    private String sql;

    /**
     * 对sql解析后的json格式
     * 存放 动态模型的 模型内容（modelContent）
     */
    @Lob
    @Column(name = "parse_json")
    private String parseJson;

    /**
     * 模型名称
     */
    @Column(name = "model_name", length = 150)
    private String modelName;

    /**
     * 模型代码
     */
    @Column(name = "model_code", length = 512)
    private String modelCode;

    /**
     * 模型描述信息
     */
    @Column(name = "description", length = 150)
    private String description;

    /**
     * 创建人所属机构id
     */
    @Column(name = "creator_institution", length = 60)
    private String creatorInstitution;

    /**
     * （参考自报表平台）机构维 存的是列的名字 比如B2，现与targetType，dimCode合并，统称orgCol，下同
     */
    @Column(name = "org_column", length = 60)
    private String orgColumn;

    /**
     * （参考自报表平台）时间维 存的是列的名字 比如C2，现与targetCode，dimName合并
     */
    @Column(name = "date_column", length = 60)
    private String dateColumn;

    /**
     * （参考自报表平台，报表平台使用的是reportUnitType 字段，这里将字段分开）数据字段，当 sqlModelType为维度表时，用来存放维度表字段; 当sqlModelType为指标表时，用来存放指标表字段
     */
    @Column(name = "data_column", length = 60)
    private String dataColumn;

    /**
     * （参考自报表平台）报表机构类型，当sqlModelType为明细表、任务表、序列表时，该字段可能有值
     * 报表机构代码都不相同则无需填写报表机构类型字段（注释摘自报表平台）
     * 存放动态模型的SQL类型：tree（树形）/ xml（XML）
     */
    @Column(name = "report_unit_type", length = 60)
    private String reportUnitType;

    /**
     * 模型的所有字段信息
     */
    @Column(name = "columns_json")
    @Lob
    private String columnsJson;

    /**
     * （参考自报表平台）维度模式，分为自动维度模式（automatic）和手动维度模式（manual）<br/>
     * 其中自动维度模式需要选择报表机构维和时间维<br/>
     * 手动维度模式则无需选择报表机构维和时间维
     */
    @Column(name = "dim_mode", length = 20)
    private String dimMode;

    /**
     * 是否可分页,默认开启自动分页
     */
    @Column(name = "pageable")
    private Boolean pageable;

    /**
     * 开启分页后，每页默认条数
     */
    @Column(name = "page_size")
    private Integer pageSize;

    /**
     * 摘自报表平台： 通过元数据保存的时候，可以将数据模型sql语句创建成视图 视图的名称与modelCode一致。通过这个字段标识来确认数据模型有没有 关联视图。默认不关联
     */
    @Column(name = "have_data_view")
    private Boolean haveDataView;

    /**
     * 允许多维表数据分析查询 --指标系统
     *
     * @since 1.1.8
     */
    @Column(name = "allow_multi_dim")
    private Boolean allowMultiDim;

    /**
     * 允许灵活查询   -- 指标系统
     *
     * @since 1.1.8
     */
    @Column(name = "allow_data_query")
    private Boolean allowDataQuery;

    /**
     * Sql模型来源类型 {@link SQLModelSourceType}
     *
     * @see SQLModelSourceType
     * @since 1.1.9
     */
    @Column(name = "sql_model_source_type")
    private Integer sqlModelSourceType;

    /**
     * sqlCriteria 修改了该标志位记录为ture,用于数据模型打开语义化时处理(旧sql模型迁移字段)
     *
     * @since 1.1.9
     */
    @Column(name = "sql_fix")
    private Boolean sqlFix;

    @Column(name = "version")
//    @Version
    private Integer version;

    /**
     * 模型是否已发布(原报表平台使用的标识)
     */
    @Column(name = "have_publish")
    private Boolean havePublish;

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

    /**
     * 语义化字段
     *
     * @since 1.4.3
     */
    @Column(name = "col_defines")
    @Lob
    private String colDefines;

    /**
     * 过滤参数
     *
     * @see com.dtsz.cm.model.vo.sqlmodel.FilterParam
     * @since 1.3.0
     */
    @Lob
    private String filterParamJson;

    /**
     * 脱敏规则json
     * @since 2.0.2
     * @see Map <String, com.dtsz.cm.model.vo.rule.DesensitizationRuleVO>
     */
    @Lob
    @Column(name = "des_rule_json")
    private String desensitizationRuleJson;

    /**
     * 字段生成方式 ： 0  单元格名称  1 自定义
     * 当前字段主要用于任务类型的数据模型
     * @since 2.0.5
     */
    @Column(name = "field_name_way")
    private Integer fieldNameWay;

    /**
     *是否进行了加密处理
     * @since 2.0.9
     */
    @Transient
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private boolean hasEncrypt;


}
