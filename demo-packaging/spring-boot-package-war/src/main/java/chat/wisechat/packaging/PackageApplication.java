package chat.wisechat.packaging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @Author Siberia.Hu
 * @Date 2025/3/13 9:30
 */
@SpringBootApplication
public class PackageApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(PackageApplication.class, args);
    }
}
