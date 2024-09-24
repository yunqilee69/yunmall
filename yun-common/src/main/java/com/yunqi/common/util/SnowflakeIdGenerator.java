package com.yunqi.common.util;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;

public class SnowflakeIdGenerator implements IdentifierGenerator {

    // 开始时间戳 (2020-01-01)
    private final long beiginTime = 1577836800000L;

    // 数据中心id和机器id所占的位数
    private final long datacenterIdBits = 5L;
    private final long workerIdBits = 5L;

    // 支持的最大数据中心id，结果是31 (这个移位算法可以快速计算出最大值)
    private final long maxDatacenterId = -1L ^ (-1L << workerIdBits);
    private long datacenterId;

    // 支持的最大机器id，结果是31 (这个移位算法可以快速计算出最大值)
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);
    private long workerId;

    // 序列在id中占的位数
    private final long sequenceBits = 12L;

    // 机器ID向左移12位
    private final long workerIdShift = sequenceBits;

    // 数据中心ID向左移17位
    private final long datacenterIdShift = sequenceBits + workerIdBits;

    // 时间截向左移22位(5+5+12)
    private final long timestampLeftShift = sequenceBits + datacenterIdBits + workerIdBits;

    // 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095)
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);

    private long lastTimestamp = -1L;

    // 0，并发控制
    private long sequence = 0L;

    public SnowflakeIdGenerator(long datacenterId, long workerId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("datacenterId Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        this.datacenterId = datacenterId;
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        this.workerId = workerId;
    }

    // 生成ID
    public synchronized long nextId() {
        long timestamp = System.currentTimeMillis();

        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        // 如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                // 阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            // 时间戳改变，毫秒内序列重置
            sequence = 0L;
        }

        // 上次生成ID的时间截
        lastTimestamp = timestamp;

        // 移位并通过或运算拼到一起组成64位的ID
        return ((timestamp - beiginTime) << timestampLeftShift) |
                (datacenterId << datacenterIdShift) |
                (workerId << workerIdShift) |
                sequence;
    }

    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }

    @Override
    public Number nextId(Object entity) {
        return this.nextId();
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
