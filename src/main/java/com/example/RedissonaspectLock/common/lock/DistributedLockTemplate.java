package com.example.RedissonaspectLock.common.lock;

import java.util.concurrent.TimeUnit;

public interface DistributedLockTemplate {

    long DEFAULT_WAIT_TIME = 30;
    long DEFAULT_TIMEOUT   = 5;
    TimeUnit DEFAULT_TIME_UNIT = TimeUnit.SECONDS;

    /**
     * Use a distributed lock, using the lock default timeout.
     * @param callback
     * @param fairLock Whether to use fair lock
     * @return
     */
    <T> T lock(DistributedLockCallback<T> callback, boolean fairLock);

    /**
     * Use distributed locks. Custom lock timeout
     *
     * @param callback
     * @param leaseTime Lock timeout. The lock is automatically released after a timeout.
     * @param timeUnit
     * @param fairLock Whether to use fair lock
     * @param <T>
     * @return
     */
    <T> T lock(DistributedLockCallback<T> callback, long leaseTime, TimeUnit timeUnit, boolean fairLock);

    /**
     * Try a distributed lock, using the lock default wait time, timeout.
     * @param callback
     * @param fairLock Whether to use fair lock
     * @param <T>
     * @return
     */
    <T> T tryLock(DistributedLockCallback<T> callback, boolean fairLock);

    /**
     * Try distributed locks, custom wait times, timeouts.
     * @param callback
     * @param waitTime Get the maximum wait time for the lock
     * @param leaseTime Lock timeout. The lock is automatically released after a timeout.
     * @param timeUnit
     * @param fairLock Whether to use fair lock
     * @param <T>
     * @return
     */
    <T> T tryLock(DistributedLockCallback<T> callback, long waitTime, long leaseTime, TimeUnit timeUnit, boolean fairLock);
}
