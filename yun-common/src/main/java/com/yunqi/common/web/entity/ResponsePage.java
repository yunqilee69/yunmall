package com.yunqi.common.web.entity;

import lombok.Data;

import java.util.List;

/**
 * 分页查询统一返回结果
 */
@Data
public class ResponsePage<T> {

    // 页面总数
    long totalPage;

    // 当前页数
    long currentPage;

    // 页大小
    long pageSize;

    // 总条数
    long totalRecords;

    // 查询结果
    List<T> records;

}
