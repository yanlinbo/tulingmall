package com.tulingxueyuan.mall.modules.file.service.impl;

import com.tulingxueyuan.mall.modules.file.service.ISysFileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Minio 文件存储
 *
 * @author ruoyi
 */
@Service
public class MinioSysFileServiceImpl implements ISysFileService {

    @Override
    public String uploadFile(MultipartFile file) {
        return null;
    }
}
