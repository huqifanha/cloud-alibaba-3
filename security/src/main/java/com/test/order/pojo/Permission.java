package com.test.order.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 角色
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@TableName("sys_permission")
public class Permission implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 备注
     */
    private String remark;

    /**
     * 权限值 ， 使用 group : perms 的写法
     */
    private String perms;

    /**
     * 属于某个分组, 一般在同一个后台页面调用的接口分为一个组
     */
    private String permsGroup;


}
