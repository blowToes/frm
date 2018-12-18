package com.example.demo.security.entity;

import java.time.LocalDate;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2018-12-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TsPermissions implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 权限名
     */
    private String permissionName;

    /**
     * 资源定义
     */
    private String permissionUrl;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private LocalDate createTime;

    /**
     * 修改时间
     */
    private LocalDate modifyTime;


}
