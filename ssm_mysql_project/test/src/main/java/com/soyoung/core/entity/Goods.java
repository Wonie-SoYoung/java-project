package com.soyoung.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
* 
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@TableName("goods")
@ApiModel(value="Goods对象", description="")
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "物理主键")
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    @ApiModelProperty(value = "名称")
    private String gname;

    @ApiModelProperty(value = "登记时间")
    private LocalDate created;

    @ApiModelProperty(value = "登记人")
    private String createdUser;

    @ApiModelProperty(value = "手机")
    private String phone;

    @ApiModelProperty(value = "分类（1公司 2行政部 3财务部 4研发部）")
    private Integer type;


}
