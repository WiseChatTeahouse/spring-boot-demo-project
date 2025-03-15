package chat.wisechat.spel.controller;

import chat.wisechat.spel.service.SpElService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author Siberia.Hu
 * @Date 2025/3/6 17:01
 */
@RestController
@RequestMapping("/spel")
public class SpElController {

    @Resource
    private SpElService spElService;

    @PostMapping("/spelParseWord")
    public void spelParseWord(@RequestBody String word) {
        spElService.spelParseWord(word);
    }

}
