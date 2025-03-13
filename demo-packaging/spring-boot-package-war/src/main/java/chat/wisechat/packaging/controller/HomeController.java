package chat.wisechat.packaging.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Siberia.Hu
 * @Date 2025/3/13 9:52
 */
@RestController
@RequestMapping("/home")
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "<h1>首页</h1>";
    }
}
