package icu.yunke.framework.web.interfaces;


import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import icu.yunke.framework.web.entity.ResponsePage;
import icu.yunke.framework.web.util.ServletUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 公用业务控制器，都需要实现增删改查操作，以及分页查询操作
 * @author
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

    /**
     * 获取分页查询page
     * @param clazz
     * @return
     * @param <T>
     */
    default <T> Page<T> getPage(Class<T> clazz) {
        Page<T> page = new Page<>();
        long currentPage = ServletUtils.getLongParameter("currentPage");
        long pageSize = ServletUtils.getLongParameter("pageSize");
        String sortField = ServletUtils.getStringParameter("sortField");
        String sortOrder = ServletUtils.getStringParameter("sortOrder");
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
    default <T> ResponsePage<T> convert2ResponsePage(Page<T> page) {
        return convert2ResponsePage(page, page.getRecords());
    }

    /**
     * 将结果转换为统一返回结果,指定结果集
     * @param page
     * @return
     */
    default <E> ResponsePage<E> convert2ResponsePage(Page<?> page, List<E> records) {
        ResponsePage<E> responsePage = new ResponsePage<>();
        responsePage.setCurrentPage(page.getCurrent());
        responsePage.setPageSize(page.getSize());
        responsePage.setTotalPage(page.getPages());
        responsePage.setTotalRecords(page.getTotal());
        responsePage.setRecords(records);
        return responsePage;
    }

}
