package com.darkstore.transfer.common.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties
public class CommonProperties {
    @Value("${redis.lock.timeout.seconds:3}")
    private int redisLockTimeoutSeconds;

    @Value("${redis.lock.acquired.seconds:1}")
    private int redisLockAcquiredSeconds;

    @Value("${trn.inner.city.transfer.max.distance:100}")
    private double innerCityTransferMaxDistance;

    public static int REDIS_LOCK_TIMEOUT_SECONDS;
    public static int REDIS_LOCK_ACQUIRED_SECONDS;
    public static double INNER_CITY_TRANSFER_MAX_DISTANCE;

    @Value("${redis.lock.timeout.seconds:3}")
    public void setRedisLockTimeoutSeconds(int value) {
        CommonProperties.REDIS_LOCK_TIMEOUT_SECONDS = value;
    }

    @Value("${redis.lock.acquired.seconds:1}")
    public void setRedisLockAcquiredSeconds(int value) {
        CommonProperties.REDIS_LOCK_ACQUIRED_SECONDS = value;
    }

    @Value("${trn.inner.city.transfer.max.distance:100}")
    public void setInnerCityTransferMaxDistance(double value) {
        CommonProperties.INNER_CITY_TRANSFER_MAX_DISTANCE = value;
    }
}
