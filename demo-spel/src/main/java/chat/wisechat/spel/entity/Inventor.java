package chat.wisechat.spel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

/**
 * @Author Siberia.Hu
 * @Date 2025/3/11 15:59
 */
@Data
@AllArgsConstructor
public class Inventor {
    private String name;
    private String nationality;
    private String[] inventions;
    private LocalDate birthdate;
    private UserInfo userInfo;
}
