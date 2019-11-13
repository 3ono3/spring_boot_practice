package com.example.demo.controller;

import com.example.demo.entity.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * @author GuoJingyuan
 * @date 2019/9/18
 */
@RestController
public class SystemController {

    @RequestMapping("/cacheUser")
    @Cacheable(value = "user-key")
    public User getUser() {
        User user = new User("张三", "zhangsan@email.com", "zhangsan013", "pass1234");
        System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");
        return user;
    }

    @RequestMapping("/cacheRe")
    @Cacheable(value = "user-key")
    public User getUserRe(){
        User user = new User("李四", "李四@email.com", "lisi025", "pass1234");
        System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");
        return user;
    }

    @RequestMapping("/uid")
    String uid(HttpSession session) {
        UUID uid = (UUID) session.getAttribute("uid");
        if (uid == null) {
            uid = UUID.randomUUID();
        }
        System.out.println("h------s------");
        session.setAttribute("uid", uid);
        return session.getId();
    }
}
