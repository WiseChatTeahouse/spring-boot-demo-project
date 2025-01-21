package chat.wisechat.minio.helper;

import chat.wisechat.minio.MinioProperties;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.MinioException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author Siberia.Hu
 * @date 2025/1/21
 */
@Slf4j
@Component
public class MinioHelper {

    @Resource
    private MinioClient minioClient;
    @Resource
    private MinioProperties minioProperties;

    public void upload(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("文件不能为空");
        }

        try (InputStream inputStream = file.getInputStream()) {
            minioClient.putObject(PutObjectArgs.builder().bucket(minioProperties.getBucket()).object(file.getOriginalFilename())
                    .stream(inputStream, file.getSize(), -1).contentType(file.getContentType())
                    .build());
        } catch (MinioException | InvalidKeyException | NoSuchAlgorithmException e) {
            log.error("upload file: {} fail，file size：{} bytes", file.getOriginalFilename(), file.getSize(), e);
        } catch (IOException e) {
            log.error("IOException upload file: {} fail，file size：{} bytes", file.getOriginalFilename(), file.getSize(), e);
        }
    }
}
