package com.soyoung.photo.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import java.io.Serializable;

/**
 * <p>
 * 字典表
 * </p>
 *
 * @author SoYoung
 * @since 2019-11-04
 */
public class Dictionary implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 类型码
     */
    private String typeCode;

    /**
     * 类型名称
     */
    private String typeName;

    /**
     * 值ID
     */
    private Integer valueId;

    /**
     * 值名称
     */
    private String valueName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    public Integer getValueId() {
        return valueId;
    }

    public void setValueId(Integer valueId) {
        this.valueId = valueId;
    }
    public String getValueName() {
        return valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }

    @Override
    public String toString() {
        return "Dictionary{" +
        "id=" + id +
        ", typeCode=" + typeCode +
        ", typeName=" + typeName +
        ", valueId=" + valueId +
        ", valueName=" + valueName +
        "}";
    }
}
