package com.druid.demo.service;

import com.druid.demo.modle.UmsAdmin;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UmsAdminService {
    UmsAdmin getUmsAdminById(Long id);

    List<UmsAdmin> findUmsAdminList(UmsAdmin umsAdmin);

    int insertUmsAdmin(UmsAdmin umsAdmin);

    void updateUmsAdmin(UmsAdmin umsAdmin);

    void batchInsertUmsAdmin(UmsAdmin umsAdmin);
}
