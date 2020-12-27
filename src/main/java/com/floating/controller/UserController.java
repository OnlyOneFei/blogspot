package com.floating.controller;


import com.floating.common.ResponseData;
import com.floating.dto.PageDTO;
import com.floating.entity.User;
import com.floating.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Mr_Fei
 * @since 2020-12-22
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 保存用户
     *
     * @param user 用户信心
     * @return com.floating.common.ResponseData
     * @author Mr_Fei
     * @date 2020/12/22 22:02
     * @description saveUser
     */
    @PostMapping(value = "/save")
    public ResponseData saveUser(@RequestBody @Validated User user) {
        userService.save(user);
        return ResponseData.success();
    }

    /**
     * 分页列表查询
     *
     * @param pageDTO pageDTO
     * @return com.floating.common.ResponseData
     * @author Mr_Fei
     * @date 2020/12/22 22:14
     * @description getUserList
     */
    @GetMapping(value = "/users")
    public ResponseData getUserList(PageDTO pageDTO) {

        return ResponseData.success();
    }
}
