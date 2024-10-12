package chat.wisechat.log.service.impl;

import chat.wisechat.log.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Override
    public String getUser() {
        log.info("UserServiceImpl getUser");
        return "Siberia.Hu";
    }
}

