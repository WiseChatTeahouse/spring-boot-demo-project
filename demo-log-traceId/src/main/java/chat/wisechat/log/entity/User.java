package chat.wisechat.log.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Author Siberia.Hu
 * @Date 2025/4/29 15:14
 */
@Data
public class User {

    private String name;
    private int age;
    private Date birthday;
    private Integer sex;

}
