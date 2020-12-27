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
 * 数据字典表
 * </p>
 *
 * @author Mr_Fei
 * @since 2020-12-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_data_dictionary")
public class DataDictionary implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 类型
     */
    private String dataType;

    /**
     * 编码
     */
    private String code;

    /**
     * 值
     */
    private String value;

    /**
     * 是否选中，0否，1是
     */
    private Integer isConfig;

    /**
     * 是否设置默认，0不设置，1设置默认
     */
    private Integer isCheck;

    /**
     * 上级id
     */
    private Integer parentId;

    /**
     * 字典备注
     */
    private String remark;

    /**
     * 是否启用，0不启用，1启用
     */
    private Integer useFlag;

    private LocalDateTime insertTime;

    private LocalDateTime updateTime;


}
