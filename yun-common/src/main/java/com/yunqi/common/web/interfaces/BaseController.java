package com.yunqi.common.web.interfaces;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yunqi.common.web.entity.RequestPage;
import com.yunqi.common.web.entity.ResponsePage;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 公用业务控制器，都需要实现增删改查操作，以及分页查询操作
 * TODO 还未完成
 */
public interface BaseController<T> {

    /**
     * 分页查询
     */
    @PostMapping("/page")
    ResponsePage<?> findPage(T obj);

    /**
     * 默认新建方法
     * @param obj
     */
    @PostMapping
    void create(T obj);

    /**
     * 默认更新方法
     * @param obj
     */
    @PutMapping
    void update(T obj);

    /**
     * 默认删除方法，支持批量删除
     * @param ids
     */
    @DeleteMapping
    void delete(List<Long> ids);

    /**
     * 默认单体查询方法
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    T findById(@PathVariable Long id);

}
