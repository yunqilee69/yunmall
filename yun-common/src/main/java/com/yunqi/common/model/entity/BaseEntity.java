package com.yunqi.common.model.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseEntity extends IDEntity{

    private LocalDateTime createAt;

    private Long createBy;

    private LocalDateTime updateAt;

    private Long updateBy;
}
