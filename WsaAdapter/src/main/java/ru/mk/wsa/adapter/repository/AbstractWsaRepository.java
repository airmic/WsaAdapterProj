package ru.mk.wsa.adapter.repository;

import org.springframework.data.redis.core.RedisTemplate;
import ru.mk.wsa.adapter.config.WsaPropertiesList;

import java.time.Duration;

public class AbstractWsaRepository implements WsaRepository {
    private final RedisTemplate<String, Object> redisTemplate;

    private final Duration timeoutSec;

    public AbstractWsaRepository(RedisTemplate<String,Object> redisTemplate
            , String serverName
            , WsaPropertiesList wsaPropertiesList) {
        this.redisTemplate = redisTemplate;
        this.timeoutSec = Duration.ofMillis(wsaPropertiesList.getServers().get(serverName).getWaitingTimeoutMs());
    }

    @Override
    public void deleteKey(String key) {
//        redisTemplate.boundHashOps(hashName).delete(key);
        redisTemplate.delete(key);
    }

    @Override
    public <T> void put(String key, T object) {
//        redisTemplate.boundHashOps(hashName).put(key, object);
        redisTemplate.boundValueOps(key).set(object, timeoutSec);
    }

    @Override
    public <T> T get(String key) {
//        return (T) redisTemplate.boundHashOps(hashName).get(key);
        return (T) redisTemplate.boundValueOps(key).get();
    }
}
