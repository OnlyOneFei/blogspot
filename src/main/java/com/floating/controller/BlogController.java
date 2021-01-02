package com.floating.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.floating.common.ResponseData;
import com.floating.common.param.Constants;
import com.floating.common.param.QueryParam;
import com.floating.dto.PageDTO;
import com.floating.entity.Blog;
import com.floating.service.BlogService;
import com.floating.util.ShiroUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Mr_Fei
 * @since 2020-12-22
 */
@RestController
@RequestMapping("/blog")
public class BlogController {


    @Resource
    private BlogService blogService;

    /**
     * 列表查询
     *
     * @param pageDTO pageDTO
     * @return com.floating.common.ResponseData
     * @author Mr_Fei
     * @date 2020/12/24 2:40
     * @description getBlogList
     */
    @GetMapping("/blogs")
    public ResponseData getBlogList(PageDTO pageDTO) {
        Page<Blog> page = new Page<>(pageDTO.getPageNo(), pageDTO.getPageSize());
        QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc(QueryParam.DatabaseColumn.INSERT_TIME);
        IPage<Blog> resultPage = blogService.page(page, queryWrapper);
        return new ResponseData<>(ResponseData.SUCCESS_CODE_SELECT, ResponseData.SUCCESS_MSG, resultPage);
    }


    /**
     * 详情查询
     *
     * @param id id
     * @return com.floating.common.ResponseData
     * @author Mr_Fei
     * @date 2020/12/24 2:40
     * @description getBlogDetail
     */
    @GetMapping("/{id}")
    public ResponseData getBlogDetail(@PathVariable(name = "id") long id) {
        Blog blog = blogService.getById(id);
        Assert.notNull(blog, "该博客已被删除！");
        return new ResponseData<>(ResponseData.SUCCESS_CODE_SELECT, ResponseData.SUCCESS_MSG, blog);
    }

    /**
     * 编辑和新增
     *
     * @param blog blog
     * @return com.floating.common.ResponseData
     * @author Mr_Fei
     * @date 2020/12/24 2:41
     * @description editBlog
     */
    @RequiresAuthentication
    @PostMapping("/edit")
    public ResponseData editBlog(@RequestBody @Validated Blog blog) {
        Blog recordBlog;
        Long id = blog.getId();
        Long userId = ShiroUtil.getProfile().getId();
        if (id == null) {
            //新增
            recordBlog = new Blog();
            recordBlog.setUserId(userId);
            recordBlog.setInsertTime(LocalDateTime.now());
            recordBlog.setUseFlag(Constants.USE_FLAG_VALID);
        } else {
            //修改
            recordBlog = blogService.getById(id);
            Assert.notNull(recordBlog, "编辑文章不存在");
            Assert.isTrue(recordBlog.getUserId().equals(userId), "没有权限编辑");
        }
        BeanUtil.copyProperties(blog, recordBlog, QueryParam.UserProperty.ID,
                QueryParam.UserProperty.USER_ID,
                QueryParam.UserProperty.INSERT_TIME,
                QueryParam.UserProperty.USE_FLAG);
        blogService.save(recordBlog);
        return ResponseData.success(ResponseData.SUCCESS_CODE_UPDATE);
    }

    /**
     * 删除操作
     *
     * @param id id
     * @return com.floating.common.ResponseData
     * @author Mr_Fei
     * @date 2020/12/24 2:57
     * @description removeById
     */
    @PostMapping("/remove")
    public ResponseData removeById(@RequestBody Long id) {
        Assert.notNull(id, "参数不正确");
        boolean removeStatus = blogService.removeById(id);
        Assert.isFalse(removeStatus, "博客不存在！");
        return ResponseData.success(ResponseData.SUCCESS_CODE_UPDATE);
    }


}
