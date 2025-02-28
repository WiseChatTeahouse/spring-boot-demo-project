package chat.wisechat.thread.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author Siberia.Hu
 * @Date 2025/2/28 10:56
 */
@EnableAsync
@Configuration
public class ThreadPoolConfig {

    @Bean(name = "defaultThreadPool")
    public TaskExecutor defaultThreadPool() {
        // 线程池
        // 常用的4种线程池：
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //Executors.newSingleThreadExecutor(); 只有一个线程
        //Executors.newFixedThreadPool(); 固定线程数
        //Executors.newScheduledThreadPool();
        //Executors.newCachedThreadPool(); 高并发
        // 通过new来创建线程池
        // 设置核心线程数
        // 业务属性来分类
        // 高密级型运算，使用 CPU核心数+1
        // io或网络型, CPU核心*2
        executor.setCorePoolSize(5);
        // 设置最大线程数
        executor.setMaxPoolSize(10);
        // 设置队列容量, 必须设置 吞吐量
        executor.setQueueCapacity(20);
        // 设置线程活跃时间（秒）, 超过核心数的线程存活时长 21线程-20核心数=1 多余什么时候会销毁？连续60秒都没有被用到则会销毁
        executor.setKeepAliveSeconds(60);
        // 设置默认线程名称前缀 日志时 为了区分框架的线程与业务线程
        executor.setThreadNamePrefix("DefaultThreadPool-");
        // 设置拒绝策略
        // 4种:
        // CallerRunsPolicy  队列满后 再提交任务时，只调用对象的run方法， 属于同步调用
        // AbortPolicy       队列满后, 再提交任务时，报错
        // DiscardPolicy     队列满后, 再提交任务时，不做任何处理
        // DiscardOldestPolicy  队列满后, 再提交任务时，淘汰最早进入队列的任务
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 等待所有任务结束后再关闭线程池
        // 关闭线程池时，如果还有线程在执行，则等待所有的线程执行完后再关闭
        executor.setWaitForTasksToCompleteOnShutdown(true);
        // 初始化
        executor.initialize();
        return executor;
    }

}
