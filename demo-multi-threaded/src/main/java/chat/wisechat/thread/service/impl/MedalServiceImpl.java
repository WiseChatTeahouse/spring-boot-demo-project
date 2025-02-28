package chat.wisechat.thread.service.impl;

import chat.wisechat.thread.service.MedalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Author Siberia.Hu
 * @Date 2025/2/28 11:03
 */
@Slf4j
@Service
public class MedalServiceImpl implements MedalService {
    @Override
    public String getMedalInfo(long userId) {
        try {
            log.info("MedalInfo");
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return String.valueOf(userId);
    }
}
