package com.koala.wms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author koala
 * @since 2023-08-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主キー
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String account;

    private String name;

    private String password;

    private Integer age;

    private Integer sex;

    private String phone;

    private Integer roleId;

    private String isValid;


}
