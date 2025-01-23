package chat.wisechat.redis.config.aspect;

import chat.wisechat.redis.common.annotation.RedisLock;
import chat.wisechat.redis.common.constants.RedisLockTypeEnum;
import chat.wisechat.redis.common.holder.RedisLockDefinitionHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Siberia.Hu
 * @date 2024/12/20
 */
@Slf4j
@Aspect
@Component
public class RedisLockAspect {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    private static final ConcurrentLinkedQueue<RedisLockDefinitionHolder> holders = new ConcurrentLinkedQueue<>();

    /*private static final ScheduledExecutorService SCHEDULER = new ScheduledThreadPoolExecutor(1,
            new BasicThreadFactory.Builder().namingPattern("redisLock-schedule-pool").daemon(true).build());*/

    /**
     * 切点
     */
    @Pointcut("@annotation(chat.wisechat.redis.common.annotation.RedisLock)")
    public void redisLockPC() {
    }

    @Around(value = "redisLockPC()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        log.info("请求进来...");
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod();

        RedisLock annotation = method.getAnnotation(RedisLock.class);
        RedisLockTypeEnum typeEnum = annotation.typeEnum();
        Object[] params = pjp.getArgs();
        String ukString = params[annotation.lockFiled()].toString();

        String businessKey = typeEnum.buildKey(ukString);
        String uniqueValue = UUID.randomUUID().toString();

        Object result = null;

        Boolean delLock = false;
        try {
            log.info("来抢锁 key：{} value: {} {} {} {}", businessKey, uniqueValue, Thread.currentThread().getName(), System.currentTimeMillis(), System.currentTimeMillis() + 10000);
            Boolean isSuccess = redisTemplate.opsForValue().setIfAbsent(businessKey, uniqueValue);
            log.info("获取锁的结果 key： {} {} {} {}", businessKey, Thread.currentThread().getName(), isSuccess, System.currentTimeMillis());
            if (Boolean.FALSE.equals(isSuccess)) {
                delLock = false;
                log.error("获取锁失败！key: {} {}", businessKey, Thread.currentThread().getName());
                throw new RuntimeException("You can,t do it, because another has get the lock =-=");
            }
            log.info("枪锁成功前 续期 {} key {} value {} 当前时间：{} - {}", Thread.currentThread().getName(), businessKey, uniqueValue, System.currentTimeMillis(), System.currentTimeMillis() + 10000);
            redisTemplate.expire(businessKey, annotation.lockTime(), TimeUnit.SECONDS);
            delLock = true;
            log.info("枪锁成功后 续期 {} key {} value {} 当前时间：{} - {}", Thread.currentThread().getName(), businessKey, uniqueValue, System.currentTimeMillis(), System.currentTimeMillis() + 10000);
            Thread currentThread = Thread.currentThread();
            // 加入延时队列
            log.info("把信息加入队列..... {}", currentThread.getName());
            holders.add(new RedisLockDefinitionHolder(businessKey, annotation.lockTime(), System.currentTimeMillis(), currentThread, annotation.tryCount()));
            // 执行业务操作
            result = pjp.proceed();
            // 线程被中断，抛出异常，中断此次请求
            if (currentThread.isInterrupted()) {
                throw new InterruptedException("You had been interrupted =-=");
            }
        } catch (InterruptedException e) {
            log.error("Interrupted Exception error!", e);
            throw new Exception("Interrupted Exception, please send request again !");
        } catch (Exception e) {
            log.error("redis lock error!", e);
        } finally {
            // 请求结束后，强制删除 key 释放锁资源
            Long expire = redisTemplate.getExpire(businessKey);
            String value = redisTemplate.opsForValue().get(businessKey);
            log.info("删除锁 expire {} value {}", expire, value);
            if (delLock)
                redisTemplate.delete(businessKey);
        }
        return result;
    }

    /*{
        SCHEDULER.scheduleAtFixedRate(() -> {
            log.info("定时任务开始执行....");
            log.info("队列任务数：{}", holders.size());
            Iterator<RedisLockDefinitionHolder> iterator = holders.iterator();
            while (iterator.hasNext()) {
                log.info("循环获取队列中排队获取锁的信息");
                RedisLockDefinitionHolder holder = iterator.next();
                // 判断
                if (holder == null) {
                    iterator.remove();
                    continue;
                }
                // 判断 key 是否还有效，无效的话进行移除
                if (redisTemplate.opsForValue().get(holder.getBusinessKey()) == null) {
                    iterator.remove();
                    continue;
                }

                // 超时重试次数，超过时给线程设定中断
                if (holder.getCurrentCount() > holder.getTryCount()) {
                    holder.getCurrentTread().interrupt();
                    iterator.remove();
                    continue;
                }
                // 判断是否进入最后三分之一时间
                long curTime = System.currentTimeMillis();
                boolean shouldExtend = (holder.getLastModifyTime() + holder.getModifyPeriod()) <= curTime;
                if (shouldExtend) {
                    holder.setLastModifyTime(curTime);
                    redisTemplate.expire(holder.getBusinessKey(), holder.getLockTime(), TimeUnit.SECONDS);
                    log.info("businessKey : [{}], try count : [{}]", holder.getBusinessKey(), holder.getCurrentCount());
                    holder.setCurrentCount(holder.getCurrentCount() + 1);
                }

            }
        }, 0, 2, TimeUnit.SECONDS);
    }*/

}
