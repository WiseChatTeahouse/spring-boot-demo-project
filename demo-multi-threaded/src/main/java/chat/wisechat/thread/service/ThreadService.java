package chat.wisechat.thread.service;

import java.util.concurrent.ExecutionException;

/**
 * @Author Siberia.Hu
 * @Date 2025/2/28 11:00
 */
public interface ThreadService {
    void supplyAsync() throws ExecutionException, InterruptedException;
}
