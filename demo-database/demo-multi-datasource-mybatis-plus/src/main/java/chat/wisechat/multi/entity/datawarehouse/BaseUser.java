package chat.wisechat.multi.entity.datawarehouse;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("base_user")
public class BaseUser {
    @TableId(type = IdType.AUTO)
    private String id;
    private String username;
    private String password;
    private String version;
    private String is_delete;
    private String create_user_id;
    private String create_user_name;
    private String update_user_id;
    private String update_user_name;
    private String create_time;
    private String update_time;
}
