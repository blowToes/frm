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
public class TsRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色名
     */
    private String roleName;

    /**
     * 角色描述
     */
    private String ruleDescription;

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
