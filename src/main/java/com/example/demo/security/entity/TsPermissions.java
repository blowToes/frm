package com.example.demo.security.entity;

import java.time.LocalDate;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import springfox.documentation.annotations.ApiIgnore;

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
@ApiModel(value = "权限",description = "权限数据模型")
public class TsPermissions implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 权限名
     */
    @ApiModelProperty(value = "权限名",example = "新增")
    private String permissionName;

    /**
     * 资源定义
     */
    @ApiModelProperty(value = "资源定义的URL",example = "localhost:8080")
    private String permissionUrl;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人",hidden = true)
    private String createUser;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间",hidden = true)
    private LocalDate createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间",hidden = true)
    private LocalDate modifyTime;


}
