package chat.wisechat.file.controller;

import chat.wisechat.file.service.FileService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author Siberia.Hu
 * @date 2024/12/20
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Resource
    private FileService fileService;

    @PostMapping("/getSha1")
    public String getSha1(@RequestParam("file") MultipartFile file) {
        return fileService.getSha1(file);
    }
}
