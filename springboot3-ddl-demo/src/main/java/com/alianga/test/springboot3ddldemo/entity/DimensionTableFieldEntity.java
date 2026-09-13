package com.alianga.test.springboot3ddldemo.entity;

import com.alianga.jkit.StringUtils;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.Objects;

/**
 * 实体类
 *
 * @author Administrator
 */
@Getter
@Setter
@Entity
@Accessors(chain = true)
@Table(name = "dimension_table_field")
public class DimensionTableFieldEntity implements Serializable {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID) @Column(name = "id", length = 32)
    private String id;

    /**
     * 名称
     */
    @Column(name = "dim_field_name", length = 50)
    private String dimFieldName;

    /**
     * 旧属性名
     */
    @Column(name = "dim_field_name_old", length = 50)
    private String dimFieldNameOld;

    @Column(name = "is_primary_key", length = 1)
    private Integer isPrimaryKey = 0;

    /**
     * 是否为文字字段
     */
    @Column(name = "is_text_key", length = 1)
    private Integer isTextKey = 0;

    /**
     * 是否为数据期字段
     */
    @Column(name = "is_time_key", length = 1)
    private Integer isTimeKey = 0;

    /**
     * 是否为公式字段
     */
    @Column(name = "is_formula_key", length = 1)
    private Integer isFormulaKey = 0;

    /**
     * 是否为sql公式字段
     */
    @Column(name = "is_sql_formula_key", length = 1)
    private Integer isSqlFormulaKey = 0;

    /**
     * 描述
     */
    @Column(name = "dim_field_desc", length = 50)
    private String dimFieldDesc;

    /**
     * 长度
     */
    @Column(name = "dim_field_length", length = 2)
    private Integer dimFieldLength;

    /**
     * 类型
     */
    @Column(name = "dim_field_type", length = 20)
    private String dimFieldType;

    /**
     * 类型
     */
    @Transient
    private String commonFieldType;

    /**
     * 小数位数
     */
    @Column(name = "dim_field_scalc", length = 2)
    private Integer dimFieldScalc;

    /**
     * 维表id
     */
    @Column(name = "dim_table_id", length = 32)
    private String dimTableId;

    /**
     * 公式
     */
    @Column(name = "aggregate_expression", length = 1024)
    private String aggregateExpression;

    /**
     * 转换类型需要的方法
     */
    @Column(name = "convert_fun", length = 10)
    private String convertFun;
    /**
     * 转换的类型
     *
     * @see ConvertTypeEnum
     */
    @Column(name = "converted_type", length = 10)
    private String convertedType;
    /**
     * 转换参数
     * 将字段转换为其他类型需要的一些参数，如，将字符串转为日期类型时需要的类型参数（yyyy/MM/dd）
     **/
    @Column(name = "convert_arg", length = 255)
    private String convertArg;

    public void setAggregateExpression(String aggregateExpression) {
        this.aggregateExpression = aggregateExpression;
        if (StringUtils.isNotEmpty(aggregateExpression)) {
            this.isFormulaKey = 1;
        }
    }

    public Integer getIsFormulaKey() {
        if (isFormulaKey != null) {
            return isFormulaKey;
        }
        if (StringUtils.isNotEmpty(aggregateExpression)) {
            isFormulaKey = 1;
        } else {
            isFormulaKey = 0;
        }

        return isFormulaKey;
    }

    public void setDimFieldType(String dimFieldType) {
        this.dimFieldType = dimFieldType;

    }

    public String getCommonFieldType() {
        if (commonFieldType != null) {
            return commonFieldType;
        }
        return commonFieldType;
    }

    public boolean isSameSqlFormula(DimensionTableFieldEntity dimensionTableFieldEntity) {
        if (dimensionTableFieldEntity == null) {
            return false;
        }
        if (this == dimensionTableFieldEntity) {
            return true;
        }
        return this.sqlFormulaHash() == dimensionTableFieldEntity.sqlFormulaHash();
    }

    public int sqlFormulaHash() {
        return Objects.hash(dimFieldName, getCommonFieldType(), aggregateExpression, dimTableId, dimFieldType);
    }

    public boolean isSameSqlField(DimensionTableFieldEntity dimensionTableFieldEntity) {
        if (dimensionTableFieldEntity == null) {
            return false;
        }
        if (this == dimensionTableFieldEntity) {
            return true;
        }
        return this.sqlFieldHash() == dimensionTableFieldEntity.sqlFieldHash();
    }

    public int sqlFieldHash() {
        return Objects.hash(dimFieldName, getCommonFieldType(), dimTableId, dimFieldType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DimensionTableFieldEntity that = (DimensionTableFieldEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(dimFieldName, that.dimFieldName) &&
                Objects.equals(isPrimaryKey, that.isPrimaryKey) &&
                Objects.equals(dimFieldLength, that.dimFieldLength) &&
                Objects.equals(dimFieldType, that.dimFieldType) &&
                Objects.equals(commonFieldType, that.commonFieldType) &&
                Objects.equals(dimFieldScalc, that.dimFieldScalc) && Objects.equals(dimTableId, that.dimTableId) &&
                Objects.equals(aggregateExpression, that.aggregateExpression) &&
                Objects.equals(convertFun, that.convertFun) && Objects.equals(convertedType, that.convertedType) &&
                Objects.equals(convertArg, that.convertArg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dimFieldName, dimFieldNameOld, isPrimaryKey, dimFieldLength, dimFieldType,
                commonFieldType, dimFieldScalc, dimTableId, aggregateExpression, convertFun, convertedType, convertArg);
    }
}
