package ht.lxj.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 集群测试类
 * */
public class RedisClusterTest extends BaseTest {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testCluster(){
        redisTemplate.execute(new RedisCallback<Integer>() {
            //这里的返回值是RedisCallback<Integer>中的泛型一值
            @Override
            public Integer doInRedis(RedisConnection redisConnection) throws DataAccessException {
                int i = 0;
                for (;i<6;i++) {
                    byte[] key = ("cluster-key-"+i).getBytes();
                    byte[] value = ("cluster-value-"+i).getBytes();
                    //往集群中设置key,value
                    redisConnection.set(key,value);
                    //根据key获取value
                    System.out.println(redisConnection.get(key));
                    //删除对应的key
                    System.out.println(redisConnection.del(key));
                }
                //这里的返回值也是RedisCallback<Integer>中的泛型一值
                return i;
            }
        });
    }
}
