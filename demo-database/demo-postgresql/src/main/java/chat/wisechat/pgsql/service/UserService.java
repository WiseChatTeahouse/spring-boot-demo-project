package chat.wisechat.pgsql.service;

public interface UserService {
    String getUser();

    String saveUser(String username, String password);
}
