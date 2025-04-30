package chat.wisechat.message.feishu;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @Author siberia.hu
 * @Package chat.wisechat.message.feishu
 * @Date 2025/3/8 11:42
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageBody {

    // 相关文档   https://open.feishu.cn/document/client-docs/bot-v3/add-custom-bot

    private String msg_type;  // 文本消息：text  富文本消息：post  群名片：share_chat  发送图片：image  飞书卡片：interactive
    private Object content;
    private MessageCard card;


}
