package com.example.demo;

import com.example.demo.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void contextLoads() {
    }

    @Test
    public void test() throws Exception{
        stringRedisTemplate.opsForValue().set("aaa", "111");
        System.out.println(stringRedisTemplate.opsForValue().get("aaa"));
        stringRedisTemplate.delete("aaa");
    }

    @Test
    public void testEntity() throws Exception{
        User user = new User("张三", "zhangsan@email.com", "zhangsan013", "pass1234");
        ValueOperations<String, User> opsVal = redisTemplate.opsForValue();
        opsVal.set("com.user", user);
        User user1 = opsVal.get("com.user");
        System.out.println(user1.getEmail());
    }
}
