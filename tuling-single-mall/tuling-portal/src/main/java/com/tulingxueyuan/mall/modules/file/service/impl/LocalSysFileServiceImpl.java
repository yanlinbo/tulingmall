package com.tulingxueyuan.mall.modules.file.service.impl;

import com.tulingxueyuan.mall.modules.file.service.ISysFileService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 本地文件存储
 *
 * @author ruoyi
 */
@Primary   //@Primary  这个注解干什么用？
@Service
public class LocalSysFileServiceImpl implements ISysFileService {
    @Override
    public String uploadFile(MultipartFile file) {
        return null;
    }
}
