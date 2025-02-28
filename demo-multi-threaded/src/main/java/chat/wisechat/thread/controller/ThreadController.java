package chat.wisechat.thread.controller;

import chat.wisechat.thread.service.ThreadService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

/**
 * @Author Siberia.Hu
 * @Date 2025/2/28 11:00
 */
@RestController
@RequestMapping("/thread")
public class ThreadController {

    @Resource
    private ThreadService threadService;

    @GetMapping("/supplyAsync")
    public void supplyAsync() throws ExecutionException, InterruptedException {
        // 获取返回值的并发编程 在获取返回值时会阻塞线程
        threadService.supplyAsync();
    }
}
