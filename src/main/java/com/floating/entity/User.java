package com.floating.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Mr_Fei
 * @since 2020-12-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String secretCode;

    /**
     * 头像信息
     */
    private String avatar;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户手机号
     */
    private String telephone;

    /**
     * 修改时间
     */
    private LocalDateTime lastLoginTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 用户状态 0正常 -1锁定
     */
    private Integer useFlag;

    /**
     * 添加时间
     */
    private LocalDateTime insertTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;


}
