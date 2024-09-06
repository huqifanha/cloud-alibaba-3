package com.test.order.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 角色权限
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@TableName("sys_role_permission")
public class RolePermission implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String id;

    private String roleId;

    private String permissionId;

}
