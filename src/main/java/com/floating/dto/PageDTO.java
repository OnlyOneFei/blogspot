package com.floating.dto;

import lombok.Data;

/**
 * @author Mr_Fei
 * @description 查询对象
 * @date 2020-12-22 22:06
 */
@Data
public class PageDTO {

    /**
     * 当前页条数
     */
    private Integer pageSize = 5;
    /**
     * 当前页
     */
    private Integer pageNo = 1;

}
