package chat.wisechat.message.feishu;

import lombok.Data;

import java.util.Map;

/**
 * @Author siberia.hu
 * @Package chat.wisechat.message.feishu
 * @Date 2025/3/8 11:49
 */
@Data
public class MessageContent {
    // 富文本消息
    private String text;
    private Map<String, String> post;
}
