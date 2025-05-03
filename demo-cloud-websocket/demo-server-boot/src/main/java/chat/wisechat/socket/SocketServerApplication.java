package chat.wisechat.socket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author siberia.hu
 * @Package PACKAGE_NAME
 * @Date 2025/5/3 18:15
 */
@EnableEurekaClient
@SpringBootApplication
public class SocketServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SocketServerApplication.class, args);
    }

}
