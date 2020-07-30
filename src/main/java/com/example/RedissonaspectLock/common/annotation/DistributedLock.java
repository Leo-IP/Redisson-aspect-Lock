package com.example.RedissonaspectLock.common.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DistributedLock {

    /**
     * The name of the lock.
     * If the lockName can be determined, set this property directly.
     */
    String lockName() default "";

    /**
     * lockName prefix
     */
    String lockNamePre() default "";
    /**
     * lockName suffix
     */
    String lockNamePost() default "lock";

    /**
     * Separator used for stitching suffixes when getting the lock name
     * @return
     */
    String separator() default ".";
    /**
     * <pre>
     * Get a property value of a parameter object of the annotation method parameter list as lockName. Because sometimes lockName is not fixed.
     * When param is not empty, you can use the argNum parameter to set the first parameter of the parameter list. If not set, the first one is taken by default.
     * </pre>
     */
    String param() default "";
    /**
     * Use the method argNum as a lock
     */
    int argNum() default 0;
    /**
     * Whether to use a fair lock.
     * Fair locks are available on a first come, first served basis.
     */
    boolean fairLock() default false;
    /**
     * Whether to use the attempt lock.
     */
    boolean tryLock() default false;
    /**
     * Maximum waiting time.
     * This field is valid only when tryLock() returns true.
     */
    long waitTime() default 30L;
    /**
     * Lock timeout.
     * After the timeout period expires, the lock is automatically released.
     * Suggest:
     * Minimize the logic that requires locking.
     */
    long leaseTime() default 5L;
    /**
     * time unit. The default is seconds.
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;


}
