package com.lihy.view.photo.controller;

import com.lihy.view.api.feign.UserApi;
import com.lihy.view.common.entity.User;
import com.lihy.view.common.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author lihy
 * @date 2018/10/11
 */
@RefreshScope
@RestController
@RequestMapping("/photo")
public class PhotoController {
    @Autowired
    private UserApi userApi;

    @GetMapping("/getUserInfoByUserId/{userId}")
    public ResponseResult<User> getUserInfoByUserId(@PathVariable String userId) {
        return userApi.getUserInfoByUserId(userId);
    }
    @Value("${profile}")
    private String profile;

    @GetMapping("/getName")
    public String getName() {
        return this.profile;
    }

    @PostMapping("/upload")
    public String fileUpload(@RequestParam MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        File fileToSave = new File(file.getOriginalFilename());
        FileCopyUtils.copy(bytes, fileToSave);
        return fileToSave.getAbsolutePath();
    }
}
