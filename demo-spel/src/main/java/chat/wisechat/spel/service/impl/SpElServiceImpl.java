package chat.wisechat.spel.service.impl;

import chat.wisechat.spel.service.SpElService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Service;

/**
 * @Author Siberia.Hu
 * @Date 2025/3/7 11:21
 */
@Slf4j
@Service
public class SpElServiceImpl implements SpElService {
    @Override
    public void spelParseWord(String word) {
        ExpressionParser parser = new SpelExpressionParser();
        // string
        String str = parser.parseExpression(word).getValue(String.class);
        log.info("str = {}", str);
        // double
        Double dou = parser.parseExpression("6.0221415E+23").getValue(Double.class);
        log.info("dou = {}", dou);
        // 解析十六进制成十进制  int类型 2147483647
        Integer value = parser.parseExpression("0x7FFFFFFF").getValue(Integer.class);
        log.info("value = {}", value);
        // false
        Boolean bool = parser.parseExpression("false").getValue(Boolean.class);
        log.info("bool = {}", bool);
        // null
        Object nullValue = parser.parseExpression("true").getValue();
        if (nullValue instanceof Boolean) {
            log.info("true");
        }
        log.info("nullValue = {}", nullValue);

    }

}
