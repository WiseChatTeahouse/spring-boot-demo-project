package chat.wisechat.minio.service.impl;

import chat.wisechat.minio.helper.MinioHelper;
import chat.wisechat.minio.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author Siberia.Hu
 * @date 2025/1/21
 */
@Service
public class FileServiceImpl implements FileService {

    @Resource
    private MinioHelper minioHelper;

    @Override
    public void upload(MultipartFile file) {
        minioHelper.upload(file);
    }
}
