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
@TableName("sys_role")
public class Role implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 角色名
     */
    private String name;

    /**
     * 角色权限字符串
     */
    private String roleKey;


    /**
     * 状态 (默认：true正常, false停用）
     */
    private Boolean status;

    /**
     * 是否删除（默认：false未删除 true已删除）
     */
    private Boolean deleted;

}
