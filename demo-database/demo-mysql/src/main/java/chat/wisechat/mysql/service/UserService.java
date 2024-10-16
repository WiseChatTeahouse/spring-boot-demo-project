package chat.wisechat.mysql.service;

import chat.wisechat.mysql.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

public interface UserService extends IService<User> {
    String getUser();

    String saveUser(String username, String password);
}
