package com.soyoung.project.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 数据返回json格式封装类
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "封装的返回值", description = "包含状态码，是否成功标识，数据，提示信息")
public class Result<T> {

    // 返回给前端的状态码
    @ApiModelProperty(value = "状态码", required = true)
    private int code;

    // 成功返回true，失败返回flase
    @ApiModelProperty(value = "是否成功标识", required = true)
    private Boolean flag;

    // 返回给前端的数据
    private transient T data;

    // 后台提示信息
    @ApiModelProperty(value = "提示信息", required = true)
    private String msg;

    /**
     * 三参构造
     *
     * @param code 状态码
     * @param flag 是否成功
     * @param msg  提示信息
     */
    public Result(int code, Boolean flag, String msg) {
        this.code = code;
        this.flag = flag;
        this.msg = msg;
    }

    /**
     * 成功返回的json封装体，没有数据
     *
     * @return json格式数据
     */
    public static Result successJson() {
        return new Result(BaseEnum.SUCCESS.getCode(), true, BaseEnum.SUCCESS.getMsg());
    }

    /**
     * 成功返回的json封装体，有数据
     *
     * @param data 返回的数据
     * @return json格式数据
     */
    public static Result successJson(Object data) {
        return new Result(BaseEnum.SUCCESS.getCode(), true, data, BaseEnum.SUCCESS.getMsg());
    }

    /**
     * 成功返回的json封装体，有数据，自定义提示信息
     *
     * @param data 返回的数据
     * @param msg  提示信息
     * @return json格式数据
     */
    public static Result successJson(Object data, String msg) {
        return new Result(BaseEnum.SUCCESS.getCode(), true, data, msg);
    }

    /**
     * 失败返回的json封装体
     *
     * @return json格式数据
     */
    public static Result errorJson() {
        return new Result(BaseEnum.FAIL.getCode(), false, BaseEnum.FAIL.getMsg());
    }


    /**
     * 失败返回的json封装体，自定义提示信息
     *
     * @return json格式数据
     */
    public static Result errorJson(String msg) {
        return new Result(BaseEnum.FAIL.getCode(), false, msg);
    }


    /**
     * 失败返回的json封装体，自定义提示信息和状态码
     *
     * @param code 状态码
     * @param msg  提示信息
     * @return json格式数据
     */
    public static Result errorJson(Integer code, String msg) {
        return new Result(code, false, msg);
    }
}
