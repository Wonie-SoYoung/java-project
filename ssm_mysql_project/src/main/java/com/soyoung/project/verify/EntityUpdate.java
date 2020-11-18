package com.soyoung.project.verify;

import javax.validation.groups.Default;

/**
 * 用于修改的分组，这个可以单独写一个包，其他实体类应用。
 * 继承的 Default ：需要验证没有分组的属性
 *
 * @ClassName EntityUpdate
 * @Description TODO
 * @Author 梅晓寒
 * @Date 2020/4/16 11:22
 * @Version 1.0
 **/
public interface EntityUpdate extends Default {
}
