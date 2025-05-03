package chat.wisechat.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author siberia.hu
 * @Package chat.wisechat.gateway
 * @Date 2025/5/3 18:14
 */
@EnableEurekaClient
@SpringBootApplication
public class SocketGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(SocketGatewayApplication.class, args);
    }
}
