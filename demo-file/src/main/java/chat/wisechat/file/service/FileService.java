package chat.wisechat.file.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Siberia.Hu
 * @date 2024/12/20
 */
public interface FileService {
    String getSha1(MultipartFile file);
    String getSha256(MultipartFile file);
}
