package chat.wisechat.multi.service.impl;

import chat.wisechat.multi.entity.datacenter.User;
import chat.wisechat.multi.entity.datawarehouse.BaseUser;
import chat.wisechat.multi.mapper.datacenter.UserMapper;
import chat.wisechat.multi.mapper.datawarehouse.BaseUserMapper;
import chat.wisechat.multi.service.TestService;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class TestServiceImpl implements TestService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private BaseUserMapper baseUserMapper;

    @Override
    public String getOne() {
        // pgsql
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getId, 3);
        User user = userMapper.selectOne(userLambdaQueryWrapper);
        // mysql
        LambdaQueryWrapper<BaseUser> baseUserLambdaQueryWrapper = new LambdaQueryWrapper<>();
        baseUserLambdaQueryWrapper.eq(BaseUser::getId, 3);
        BaseUser baseUser = baseUserMapper.selectOne(baseUserLambdaQueryWrapper);
        // 转JSONString
        String userStr = JSON.toJSONString(user);
        String baseUserStr = JSON.toJSONString(baseUser);
        log.info("result = {}", userStr + baseUserStr);
        // 输出
        return userStr + baseUserStr;
    }
}
