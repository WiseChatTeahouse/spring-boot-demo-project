package chat.wisechat.file.service.impl;

import chat.wisechat.file.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * @author Siberia.Hu
 * @date 2024/12/20
 */
@Slf4j
@Service
public class FileServiceImpl implements FileService {
    @Override
    public String getSha1(MultipartFile file) {
        try (InputStream in = file.getInputStream()) {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            byte[] buffer = new byte[1024 * 1024 * 10];
            int len = 0;
            while ((len = in.read(buffer)) > 0) {
                digest.update(buffer, 0, len);
            }
            StringBuilder sha1 = new StringBuilder(new BigInteger(1, digest.digest()).toString(16));
            int length = 40 - sha1.length();
            if (length > 0) {
                for (int i = 0; i < length; i++) {
                    sha1.insert(0, "0");
                }
            }
            return sha1.toString();
        } catch (Exception e) {
            log.error("文件读取异常");
        }
        return "";
    }
}
