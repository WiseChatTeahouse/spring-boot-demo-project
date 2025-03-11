package chat.wisechat.spel.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Siberia.Hu
 * @Date 2025/3/11 15:57
 */
@Data
public class SocietyIdentity {
    private String name;
    public static String ADVISORS = "Advisors";
    public static String PRESIDENT = "President";

    private List<Inventor> members = new ArrayList<>();
    private Map<String, Object> officers = new HashMap<>();

    public boolean isMember(String name) {
        for (Inventor inventor : members) {
            if (inventor.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
