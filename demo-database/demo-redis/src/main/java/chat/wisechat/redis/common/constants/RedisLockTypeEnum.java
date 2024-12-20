package chat.wisechat.redis.common.constants;

import lombok.Getter;

/**
 * @author Siberia.Hu
 * @date 2024/12/20
 */
@Getter
public enum RedisLockTypeEnum {

    ONE("Business1", "Test1"),
    TWO("Business2", "Test2");

    private final String code;
    private final String desc;

    RedisLockTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String buildKey(String key) {
        return String.format("%s:%s", this.getCode(), key);
    }
}
