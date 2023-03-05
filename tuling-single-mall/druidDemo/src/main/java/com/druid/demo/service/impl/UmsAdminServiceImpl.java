package com.druid.demo.service.impl;

import com.druid.demo.mapper.UmsAdminMapper;
import com.druid.demo.modle.UmsAdmin;
import com.druid.demo.service.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UmsAdminServiceImpl implements UmsAdminService {
    @Autowired
    UmsAdminMapper umsAdminMapper;
    @Override
    public UmsAdmin getUmsAdminById(Long id) {

        return umsAdminMapper.getUmsAdminById(id);
    }

    @Override
    public List<UmsAdmin> findUmsAdminList(UmsAdmin umsAdmin) {
        return umsAdminMapper.findUmsAdminList(umsAdmin);
    }

    @Override
    public int insertUmsAdmin(UmsAdmin umsAdmin) {
        return umsAdminMapper.insertUmsAdmin(umsAdmin);
    }

    @Override
    public void updateUmsAdmin(UmsAdmin umsAdmin) {
        umsAdminMapper.updateUmsAdmin(umsAdmin);
    }

    @Override
    public void batchInsertUmsAdmin(UmsAdmin umsAdmin) {
        umsAdminMapper.batchInsertUmsAdmin(umsAdmin);
    }
}
