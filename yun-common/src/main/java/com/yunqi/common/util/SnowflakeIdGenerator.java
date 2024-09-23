package com.yunqi.common.util;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SnowflakeIdGenerator implements IdentifierGenerator {
    @Override
    public Number nextId(Object entity) {
        // 获取当前日期和时间
        LocalDateTime now = LocalDateTime.now();

        // 创建DateTimeFormatter格式化对象
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

        // 格式化日期和时间为指定的格式
        String formattedDateTime = now.format(formatter);

        // 将格式化后的日期时间字符串转换为Long类型
        return Long.parseLong(formattedDateTime);
    }

    @Override
    public String nextUUID(Object entity) {
        return IdentifierGenerator.super.nextUUID(entity);
    }

    @Override
    public boolean assignId(Object idValue) {
        return IdentifierGenerator.super.assignId(idValue);
    }
}
