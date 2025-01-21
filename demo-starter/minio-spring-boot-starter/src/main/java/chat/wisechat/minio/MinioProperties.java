package chat.wisechat.minio;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Siberia.Hu
 * @date 2025/1/21
 */
@Data
@ConfigurationProperties(prefix = "wisechat.file.minio")
public class MinioProperties {

    private String url;
    private String accessKey;
    private String secretKey;
    private String bucket;

}
