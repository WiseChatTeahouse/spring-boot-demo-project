package chat.wisechat.minio.helper;

import chat.wisechat.minio.MinioProperties;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import io.minio.errors.MinioException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
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

    public void upload(File file) {
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            log.info("file local info {}", file);
            minioClient.putObject(PutObjectArgs.builder().bucket(minioProperties.getBucket()).object(file.getName())
                    .stream(fileInputStream, file.length(), -1).build());
        } catch (Exception e) {
            log.error("upload file fail，file size：{} bytes", file.length(), e);
        }
    }

    /**
     * 指定contentType类型的文件上传
     *
     * @param file        文件
     * @param contentType 类型
     */
    public void upload(File file, String contentType) {
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            log.info("file local info {}", file);
            ObjectWriteResponse objectWriteResponse = minioClient.putObject(PutObjectArgs.builder().bucket(minioProperties.getBucket()).object(file.getName())
                    .stream(fileInputStream, file.length(), -1).contentType(contentType).build());
            String sha1 = objectWriteResponse.checksumSHA1();
            log.info("file info {}", sha1);
        } catch (Exception e) {
            log.error("upload file fail，file size：{} bytes", file.length(), e);
        }
    }

    /**
     * 指定contentType类型和文件名的文件上传
     *
     * @param file        文件
     * @param contentType 类型
     * @param fileName    文件名
     */
    public void upload(File file, String contentType, String fileName) {
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            log.info("file local info {}", file);
            ObjectWriteResponse objectWriteResponse = minioClient.putObject(PutObjectArgs.builder().bucket(minioProperties.getBucket()).object(fileName)
                    .stream(fileInputStream, file.length(), -1).contentType(contentType).build());
            String sha1 = objectWriteResponse.checksumSHA1();
            log.info("file info {}", sha1);
        } catch (Exception e) {
            log.error("upload file fail，file size：{} bytes", file.length(), e);
        }
    }
}
