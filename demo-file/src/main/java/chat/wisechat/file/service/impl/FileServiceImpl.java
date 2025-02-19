package chat.wisechat.file.service.impl;

import chat.wisechat.file.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Siberia.Hu
 * @date 2024/12/20
 */
@Slf4j
@Service
public class FileServiceImpl implements FileService {
    // DeepSeek优化后的代码
    private static final String SHA1_ALGORITHM = "SHA-1";
    private static final String SHA256_ALGORITHM = "SHA-256";
    private static final int BUFFER_SIZE = 10 * 1024 * 1024; // 10MB 缓冲区

    @Override
    public String getSha1(MultipartFile file) {
        return calculateHash(file, SHA1_ALGORITHM, 40);
    }

    @Override
    public String getSha256(MultipartFile file) {
        return calculateHash(file, SHA256_ALGORITHM, 64);
    }

    private String calculateHash(MultipartFile file, String algorithm, int expectedLength) {
        try (InputStream in = file.getInputStream()) {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead;

            while ((bytesRead = in.read(buffer)) != -1) {
                digest.update(buffer, 0, bytesRead);
            }

            byte[] hashBytes = digest.digest();
            return String.format("%0" + expectedLength + "x", new BigInteger(1, hashBytes));
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("Unsupported algorithm: " + algorithm, e);
        } catch (IOException e) {
            log.error("文件处理失败 [算法:{}]: {}", algorithm, e.getMessage(), e);
            throw new RuntimeException("File processing failed", e);
        }
    }
}
