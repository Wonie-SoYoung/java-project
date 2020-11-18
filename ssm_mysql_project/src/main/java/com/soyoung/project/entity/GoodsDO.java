package com.soyoung.project.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import com.soyoung.project.verify.EntityUpdate;
import com.soyoung.project.verify.custom.impl.FlagValidator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
* 
*/
@Data                                       //实体
@EqualsAndHashCode(callSuper = false)       //此注解会生成equals(Object other) 和 hashCode()方法。
@Accessors(chain = true)                    //chain为一个布尔值，如果为true生成的set方法返回this，为false生成的set方法是void类型
@AllArgsConstructor                         //全参
@NoArgsConstructor                          //空参
@Builder                                    //实体构造器
@JsonInclude(value = JsonInclude.Include.NON_NULL)  //jackson 实体转json 为NULL的字段不参加序列化
@TableName("goods")             //表名
@ApiModel(value="Goods对象", description="")
public class GoodsDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "物理主键")
    @TableId(value = "id", type = IdType.AUTO)
    @NotNull(message = "id不能为空",groups = {EntityUpdate.class})
    private Integer id;

    @ApiModelProperty(value = "名称")
    @NotEmpty(message = "物品名称不能为空")
    private String gname;

    @ApiModelProperty(value = "登记时间")
    @NotNull(message = "登记时间不能为空")
    private Date created;

    @ApiModelProperty(value = "登记人")
    @NotEmpty(message = "登记人不能为空")
    private String createdUser;

    @ApiModelProperty(value = "手机")
    private String phone;

    @ApiModelProperty(value = "分类（1公司 2行政部 3财务部 4研发部）")
    @FlagValidator(message = "分类不正确",values = "1,2,3,4")
    private Integer type;


}
