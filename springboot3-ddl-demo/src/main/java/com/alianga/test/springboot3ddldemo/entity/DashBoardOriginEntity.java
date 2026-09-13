package com.alianga.test.springboot3ddldemo.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author long
 * @version 1.0.0
 * @name DashBoardOriginEntity.java 普通类
 * @description 报告数据来源
 * @date 2021年06月07日 - 15:03
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Accessors(chain = true)
@Table(name = "dash_board_origin")
public class DashBoardOriginEntity implements Serializable {

    private static final long serialVersionUID = 7955659382777208050L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid.hex")
    @Column(name = "id", length = 64)
    private String id;

    @Column(name = "user_id")
    private String userId;

    /**
     * 报告ID
     */
    @Column(name = "dash_id")
    private String dashId;

    /** ======================================================== */

    /**
     * 报告的sheet页
     */
    @Column(name = "sheet")
    private Integer sheet;

    /** ======================================================== */

    /**
     * 数据集ID
     */
    @Column(name = "theme_id")
    private String themeId;

    /**
     * 组件标识，存放用到主题表或者维表的组件的id
     */
    @Column(name = "comp_id")
    private String compId;

    /**
     * 主题表ID
     */
    @Column(name = "sub_id")
    private String subId;

    /**
     * 维度ids, 多个维度通过逗号进行分割
     */
    @Column(name = "dimen_ids")
    private String dimenIds;

    /** ======================================================== */

    /**
     * 数据状态
     */
    @Column(name = "can_view")
    private Boolean canView;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        DashBoardOriginEntity that = (DashBoardOriginEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
