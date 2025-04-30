package chat.wisechat.message.feishu;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @Author siberia.hu
 * @Package chat.wisechat.message.feishu
 * @Date 2025/3/8 12:03
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageCard {

    // 相关文档  https://open.feishu.cn/document/uAjLw4CM/ukzMukzMukzM/feishu-cards/quick-start/send-message-cards-with-custom-bot
    // 发送模板卡片
    private String type;
    private MessageCardTemp data;

    // 相关文档  https://open.feishu.cn/document/client-docs/bot-v3/add-custom-bot
    // 发送非模板卡片
    private String schema;
    private Object config;
    private Object body;
    private Object header;

}
