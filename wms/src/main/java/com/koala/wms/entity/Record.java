package com.koala.wms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author koala
 * @since 2023-10-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Record implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer goods;

    private Integer userId;

    private Integer adminId;

    private Integer count;

    private LocalDateTime createtime;

    private String remark;

    @TableField(exist = false)
    private String action;
}
