package com.yunqi.common.util;

import ch.qos.logback.classic.spi.EventArgUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yunqi.common.web.entity.ResponsePage;

import java.util.List;

/**
 * 分页查询工具类
 */
public class PageUtil {

    /**
     * 获取分页查询page
     * @param clazz
     * @return
     * @param <T>
     */
    public static <T> Page<T> getPage(Class<T> clazz) {
        Page<T> page = new Page<>();
        long currentPage = ServletUtil.getLongParameter("currentPage");
        long pageSize = ServletUtil.getLongParameter("pageSize");
        String sortField = ServletUtil.getStringParameter("sortField");
        String sortOrder = ServletUtil.getStringParameter("sortOrder");
        // 设置默认值
        if (currentPage < 1) {
            currentPage = 1;
        }
        if (pageSize < 1) {
            pageSize = 20;
        }
        page.setCurrent(currentPage);
        page.setSize(pageSize);
        // 如果提供了排序字段，则设置排序规则
        if (sortField != null && !sortField.isEmpty()) {
            boolean isAsc = "asc".equalsIgnoreCase(sortOrder);
            page.addOrder(isAsc ? OrderItem.asc(sortField) : OrderItem.desc(sortField));
        }
        return page;
    }

    /**
     * 将结果转换为统一返回结果
     * @param page
     * @return
     */
    public static <T> ResponsePage<T> convert2ResponsePage(Page<T> page) {
        return convert2ResponsePage(page, page.getRecords());
    }

    /**
     * 将结果转换为统一返回结果,指定结果集
     * @param page
     * @return
     */
    public static <E> ResponsePage<E> convert2ResponsePage(Page<?> page, List<E> records) {
        ResponsePage<E> responsePage = new ResponsePage<>();
        responsePage.setCurrentPage(page.getCurrent());
        responsePage.setPageSize(page.getSize());
        responsePage.setTotalPage(page.getPages());
        responsePage.setTotalRecords(page.getTotal());
        responsePage.setRecords(records);
        return responsePage;
    }
}
