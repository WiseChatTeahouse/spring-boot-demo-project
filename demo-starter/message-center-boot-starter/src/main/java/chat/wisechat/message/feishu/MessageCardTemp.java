package chat.wisechat.message.feishu;

import lombok.Data;

import java.util.Map;

/**
 * @Author siberia.hu
 * @Package chat.wisechat.message.feishu
 * @Date 2025/3/8 12:21
 */
@Data
public class MessageCardTemp {
    // 相关文档  https://open.feishu.cn/document/uAjLw4CM/ukzMukzMukzM/feishu-cards/quick-start/send-message-cards-with-custom-bot
    // 飞书机器人发送使用模板的卡片消息
    private String template_id;
    private String template_version_name;
    private Map<String, Object> template_variable;

}
