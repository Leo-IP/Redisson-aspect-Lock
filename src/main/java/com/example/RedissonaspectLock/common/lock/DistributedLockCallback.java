package com.example.RedissonaspectLock.common.lock;

public interface DistributedLockCallback<T> {
    /**
     * The caller must implement business logic in this method that requires a distributed lock
     *
     * @return
     */

    public T process();

    /**
     * Get the distributed lock name
     *
     * @return
     *
     */

    public String getLockName();
}
