package com.alianga.test.springboot3ddldemo.enums;

/**
 * 维度类型
 *
 * @author Administrator
 */
public enum DimType {
    INDICATORLIBRARY(0), // 指标库
    DIM(1), // 维度
    DIMGROUP(2); // 维度分组

    int id;

    DimType(int id) {
        this.id = id;
    }

    public static DimType getById(String id) {
        try {
            int dimTypeId = Integer.parseInt(id);
            DimType[] values = DimType.values();
            for (DimType dimType : values) {
                if (dimType.getId() == dimTypeId) {
                    return dimType;
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public int getId() {
        return id;
    }
}
