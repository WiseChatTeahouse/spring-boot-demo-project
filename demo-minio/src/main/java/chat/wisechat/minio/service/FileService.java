package chat.wisechat.minio.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Siberia.Hu
 * @date 2025/1/21
 */
public interface FileService {
    void upload(MultipartFile file);

    void upload();
}
