package com.tulingxueyuan.mall.modules.file.service;

import org.springframework.web.multipart.MultipartFile;

public interface ISysFileService {
    String uploadFile(MultipartFile file);
}
