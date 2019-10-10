
package com.allen.idc;

public class IdCreater {
    private static final long idepoch = 1288834974657L;
    private static final long maxWorkerId = 31L;
    private static final long maxDatacenterId = 255L;
    private static final long workerIdShift = 9L;
    private static final long datacenterIdShift = 14L;
    private static final long timestampLeftShift = 22L;
    private static final long sequenceMask = 511L;
    private static long lastTimestamp = -1L;
    private long sequence = 0L;
    private final long workerId;
    private final long datacenterId;

    public IdCreater(long workerId, long datacenterId) {
        if (workerId <= 31L && workerId >= 0L) {
            if (datacenterId <= 255L && datacenterId >= 0L) {
                this.workerId = workerId;
                this.datacenterId = datacenterId;
            } else {
                throw new IllegalArgumentException("datacenter Id can't be greater than 255 or less than 0");
            }
        } else {
            throw new IllegalArgumentException("worker Id can't be greater than 31 or less than 0");
        }
    }

    public IdCreater(long workerId) {
        if (workerId <= 31L && workerId >= 0L) {
            this.workerId = workerId;
            this.datacenterId = 0L;
        } else {
            throw new IllegalArgumentException("worker Id can't be greater than 31 or less than 0");
        }
    }

    public long nextId() {
        return this.nextId(false, 0L);
    }

    public long nextId(long busid) {
        if (busid <= 255L && busid >= 0L) {
            return this.nextId(true, busid);
        } else {
            throw new IllegalArgumentException("datacenter Id can't be greater than 255 or less than 0");
        }
    }

    private synchronized long nextId(boolean isPadding, long busid) {
        long timestamp = this.timeGen();
        long paddingnum = this.datacenterId;
        if (isPadding) {
            paddingnum = busid;
        }

        if (timestamp < lastTimestamp) {
            throw new RuntimeException("Clock moved backwards.  Refusing to generate id for " + (lastTimestamp - timestamp) + " milliseconds");
        } else {
            this.sequence = this.sequence + 1L & 511L;
            if (lastTimestamp == timestamp && this.sequence == 0L) {
                timestamp = this.tilNextMillis(lastTimestamp);
            }

            lastTimestamp = timestamp;
            long nextId = timestamp - 1288834974657L << 22 | paddingnum << 14 | this.workerId << 9 | this.sequence;
            return nextId;
        }
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp;
        for (timestamp = this.timeGen(); timestamp <= lastTimestamp; timestamp = this.timeGen()) {
        }

        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }
}
