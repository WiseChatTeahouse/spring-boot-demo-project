package chat.wisechat.minio.controller;

import chat.wisechat.minio.service.FileService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author Siberia.Hu
 * @date 2025/1/21
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Resource
    private FileService fileService;

    /**
     * 简单的文件上传
     *
     * @param file 文件
     */
    @PostMapping("/upload")
    public void upload(@RequestBody MultipartFile file) {
        fileService.upload(file);
    }

    @GetMapping("/bondsInfo")
    public void bondsInfo() {
        fileService.upload();
    }

}
