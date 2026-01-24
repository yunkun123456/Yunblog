package com.ssj.yunblog.fileService.controller;

import com.ssj.yunblog.common.entity.Result;
import com.ssj.yunblog.fileService.service.FileService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件服务控制层
 *
 * @author: yunkun
 * @Date: 2025/9/13
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Resource
    private FileService fileService;

    /**
     * 文件上传服务接口
     */
    @PostMapping("/upload")
    public Result<String> upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return Result.fail("上传文件为空");
        }
        try {
            String fileName = fileService.upload(file.getInputStream(), file.getOriginalFilename());
            return Result.ok(fileName, "文件上传成功！");
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
            return Result.fail("上传文件失败:" + e);
        }
    }

    /**
     * 文件下载服务接口
     */
    @GetMapping("/download/{fileName}")
    public ResponseEntity<org.springframework.core.io.Resource> download(@PathVariable String fileName) {
        try {
            org.springframework.core.io.Resource resource = fileService.download(fileName);
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(fileService.getMedisType(fileName)))
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }
}
