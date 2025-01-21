package chat.wisechat.minio.service.impl;

import chat.wisechat.minio.entity.Student;
import chat.wisechat.minio.helper.MinioHelper;
import chat.wisechat.minio.service.FileService;
import cn.idev.excel.FastExcel;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Collections;

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

    @Override
    public void upload() {

        try {
            File tempFile = File.createTempFile("sim", ".xlsx");

            FastExcel.write(tempFile, Student.class).sheet().doWrite(Collections.singleton(new Student("张三", 18, "男")));

            minioHelper.upload(tempFile);

            tempFile.deleteOnExit();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
