package com.oujiong.controller;

import com.oujiong.redisson.RedissonLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Description: 不基于注解方式锁操作
 *
 * @author xub
 * @date 2019/6/19 下午6:01
 */
@RestController
@Slf4j
public class LockController {

    @Autowired
    RedissonLock redissonLock;

    /**
     * 模拟这个是商品库存
     */
    public static volatile Integer TOTAL = 10;


    @GetMapping("lock-decrease-stock")
    public String lockDecreaseStock() throws InterruptedException {
        redissonLock.lock("lock", 10L);
        if (TOTAL > 0) {
            TOTAL--;
        }
        Thread.sleep(50);
        log.info("===lock===减完库存后,当前库存===" + TOTAL);
        //如果该线程还持有该锁，那么释放该锁。如果该线程不持有该锁，说明该线程的锁已到过期时间，自动释放锁
        if (redissonLock.isHeldByCurrentThread("lock")) {
            redissonLock.unlock("lock");
        }
        return "=================================";
    }

    @GetMapping("trylock-decrease-stock")
    public String trylockDecreaseStock() throws InterruptedException {
        Long id   = Thread.currentThread().getId();
        if (redissonLock.tryLock("trylock", 3L,3L)) {
            log.info("====tryLock=== " +id );
            Thread.sleep(10000);
            if(redissonLock.isHeldByCurrentThread("trylocl"))
                redissonLock.unlock("trylock");
            log.info("====  tryLock   unlock=== " + id );
        }
        else
            {
            log.info("[ExecutorRedisson]获取锁失败"   + Thread.currentThread().getId());
        }
        return "===================================";
    }




    @GetMapping("trylock-stock")
    public String trylockStock() throws InterruptedException {

        if (redissonLock.tryLock("trylock", 5L))
        {

            String  id   = Thread.currentThread().getName();

            Long   idd   = Thread.currentThread().getId();
            log.info("tryLock   get lock=== " + id     +"     "   +idd);
            Thread.sleep(2000);
            if(redissonLock.isHeldByCurrentThread("trylock"))
            {
                redissonLock.unlock("trylock");
                //log.info("tryLock   unlock=== " + id );
            }
        }
        else
        {
            log.info("[ExecutorRedisson]获取锁失败"   + Thread.currentThread().getId());
        }
        return "===================================";
    }




    public static void main(String[] args) {
        long time = TimeUnit.SECONDS.toMillis(5L);
        System.out.println(time);
        long current = System.currentTimeMillis();

        time -= (System.currentTimeMillis() - current);
        System.out.println(time);
        if (time <= 0) {
            System.out.println(222222);
        }


        int a =3;
        a -=4;
        System.out.println(a);
    }



}
