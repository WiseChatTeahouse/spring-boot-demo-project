package chat.wisechat.redis.common.holder;

import lombok.Data;

/**
 * @author Siberia.Hu
 * @date 2024/12/20
 */
@Data
public class RedisLockDefinitionHolder {

    /**
     * 业务key
     */
    private String businessKey;
    /**
     * 加锁时间 （秒 s）
     */
    private Long lockTime;
    /**
     * 上次更新时间 （ms）
     */
    private Long lastModifyTime;
    /**
     * 保存当前线程
     */
    private Thread currentTread;
    /**
     * 总共尝试次数
     */
    private int tryCount;
    /**
     * 当前尝试次数
     */
    private int currentCount;
    /**
     * 更新的时间周期（毫秒）,公式 = 加锁时间（转成毫秒） / 3
     */
    private Long modifyPeriod;

    public RedisLockDefinitionHolder(String businessKey, Long lockTime, Long lastModifyTime, Thread currentTread, int tryCount) {
        this.businessKey = businessKey;
        this.lockTime = lockTime;
        this.lastModifyTime = lastModifyTime;
        this.currentTread = currentTread;
        this.tryCount = tryCount;
    }
}
