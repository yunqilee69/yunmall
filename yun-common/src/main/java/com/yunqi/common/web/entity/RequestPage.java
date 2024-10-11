package com.yunqi.common.web.entity;

import lombok.Data;

/**
 * 分页查询统一查询类
 */
@Data
public class RequestPage {

    // 当前页数
    long currentPage;

    // 页大小
    long pageSize;

    // 排序字段
    String sortField;

    // 排序方式,默认升序
    String sortOrder = "asc";
}
