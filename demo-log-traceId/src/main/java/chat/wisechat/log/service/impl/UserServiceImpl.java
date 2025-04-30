package chat.wisechat.log.service.impl;

import chat.wisechat.log.entity.User;
import chat.wisechat.log.service.UserService;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Override
    public User getUser() {
        log.info("UserServiceImpl getUser");
        User user = new User();
        user.setAge(18);
        user.setName("Siberia.Hu");
        user.setBirthday(new Date());
        log.info(JSON.toJSONString(user));
        return user;
    }
}

