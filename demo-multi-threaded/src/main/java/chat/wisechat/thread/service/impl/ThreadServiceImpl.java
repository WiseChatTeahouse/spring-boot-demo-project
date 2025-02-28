package chat.wisechat.thread.service.impl;

import chat.wisechat.thread.service.MedalService;
import chat.wisechat.thread.service.ThreadService;
import chat.wisechat.thread.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @Author Siberia.Hu
 * @Date 2025/2/28 11:00
 */
@Slf4j
@Service
public class ThreadServiceImpl implements ThreadService {

    @Resource
    private UserService userService;
    @Resource
    private MedalService medalService;
    @Resource
    private TaskExecutor defaultThreadPool;

    @Override
    public void supplyAsync() throws ExecutionException, InterruptedException {
        long timeMillis = System.currentTimeMillis();
        CompletableFuture<String> userInfo = CompletableFuture.supplyAsync(() -> userService.getUserInfo(123), defaultThreadPool);

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        CompletableFuture<String> medalInfo = CompletableFuture.supplyAsync(() -> medalService.getMedalInfo(123), defaultThreadPool);
        // 在get时会阻塞线程
        String user = userInfo.get();
        String medal = medalInfo.get();
        log.info("time = {} user = {} medal = {}", (System.currentTimeMillis() - timeMillis), user, medal);
    }

    @Override
    public void runAsync() {
        long timeMillis = System.currentTimeMillis();
        // runAsync 没有返回值，“get()或者join()时” 依然会阻塞线程
        CompletableFuture<Void> userInfo = CompletableFuture.runAsync(() -> userService.getUserInfo(123), defaultThreadPool);

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        CompletableFuture<String> medalInfo = CompletableFuture.supplyAsync(() -> medalService.getMedalInfo(123), defaultThreadPool);
        // 在join时会阻塞线程 用于无需处理异常的场景
        Void join = userInfo.join();
        log.info("------分界点------");
        String medal = medalInfo.join();
        log.info("time = {} user = {} medal = {}", (System.currentTimeMillis() - timeMillis), join, medal);
    }
}
