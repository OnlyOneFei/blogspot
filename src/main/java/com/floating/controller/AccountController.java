package com.floating.controller;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.floating.common.ResponseData;
import com.floating.common.param.Constants;
import com.floating.common.param.ErrorMsg;
import com.floating.common.param.QueryParam;
import com.floating.util.JwtUtils;
import com.floating.dto.LoginDTO;
import com.floating.entity.User;
import com.floating.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Mr_Fei
 * @description 登录逻辑处理
 * @date 2020-12-22 22:17
 */
@RestController
public class AccountController {

    @Resource
    private JwtUtils jwtUtils;
    @Resource
    private UserService userService;

    /**
     * 登录方法
     *
     * @param loginDTO loginDto
     * @param response response
     * @return com.floating.common.ResponseData
     * @author Mr_Fei
     * @date 2020/12/22 22:22
     * @description login
     */
    @CrossOrigin
    @PostMapping("/login")
    public ResponseData login(@Validated @RequestBody LoginDTO loginDTO, HttpServletResponse response) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq(QueryParam.USERNAME.toLowerCase(), loginDTO.getUsername());
        userQueryWrapper.eq(QueryParam.USE_FLAG.toLowerCase(), Constants.USE_FLAG_VALID);
        User user = userService.getOne(userQueryWrapper);
        Assert.notNull(user, ErrorMsg.USERNAME_NOT_FOUND_MSG);
        String anObject = SecureUtil.md5(loginDTO.getPassword());
        if (!user.getSecretCode().equals(anObject)) {
            return ResponseData.failed(ErrorMsg.PWD_ERROR_MSG);
        }
        String jwt = jwtUtils.generateToken(user.getId());
        response.setHeader("Authorization", jwt);
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        // 用户可以另一个接口
        return ResponseData.success(MapUtil.builder()
                .put(QueryParam.ID.toLowerCase(), user.getId())
                .put(QueryParam.USERNAME.toLowerCase(), user.getUsername())
                .put(QueryParam.AVATAR.toLowerCase(), user.getAvatar())
                .put(QueryParam.EMAIL.toLowerCase(), user.getEmail())
                .map().toString()
        );
    }

    /**
     * 退出接口
     *
     * @return com.floating.common.ResponseData
     * @author Mr_Fei
     * @date 2020/12/22 22:20
     * @description logout
     */
    @GetMapping("/logout")
    @RequiresAuthentication
    public ResponseData logout() {
        SecurityUtils.getSubject().logout();
        return ResponseData.success();
    }
}

