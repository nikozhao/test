package demo.lock;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @Author: Niko Zhao
 * @Date: Create in 14:30 11/30/17
 */
@Service
public class RedisGobalLock implements GobalLock, InitializingBean {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    RedisSerializer<String> redisSerializer;

    @Override
    public boolean lock(Object key) {
        Assert.notNull(key, "RedisGobalLock key must not null");
        return stringRedisTemplate.execute(new RedisCallback<Boolean>() {
            @Nullable
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.setNX(redisSerializer.serialize(key.toString()), redisSerializer.serialize(key.toString()));
            }
        });
    }

    @Override
    public boolean unLock(Object key) {
        Assert.notNull(key, "RedisGobalLock key must not null");
        return stringRedisTemplate.execute(new RedisCallback<Boolean>() {
            @Nullable
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.del(redisSerializer.serialize(key.toString()))>0;
            }
        });
    }

    @Override
    public boolean expire(Object key, Long time) {
        Assert.notNull(key, "RedisGobalLock key must not null");
        return stringRedisTemplate.execute(new RedisCallback<Boolean>() {
            @Nullable
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.expire(redisSerializer.serialize(key.toString()), time);
            }
        });
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.redisSerializer =stringRedisTemplate.getStringSerializer();
    }
}
