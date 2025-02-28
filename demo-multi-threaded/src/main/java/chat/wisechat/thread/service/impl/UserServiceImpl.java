package chat.wisechat.thread.service.impl;

import chat.wisechat.thread.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Author Siberia.Hu
 * @Date 2025/2/28 11:03
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Override
    public String getUserInfo(long userId) {
        try {
            log.info("UserInfo");
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return String.valueOf(userId);
    }
}
