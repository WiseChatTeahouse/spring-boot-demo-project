package chat.wisechat.pgsql.service.impl;

import chat.wisechat.pgsql.entity.User;
import chat.wisechat.pgsql.mapper.UserMapper;
import chat.wisechat.pgsql.service.UserService;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public String getUser() {
        return JSON.toJSONString(list());
    }


    @Override
    public String saveUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return String.valueOf(save(user));
    }
}
