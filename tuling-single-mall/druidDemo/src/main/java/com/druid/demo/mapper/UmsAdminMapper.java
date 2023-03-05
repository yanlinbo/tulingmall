package com.druid.demo.mapper;


import com.druid.demo.modle.UmsAdmin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UmsAdminMapper {

    UmsAdmin getUmsAdminById(Long id);

    int insertUmsAdmin(UmsAdmin umsAdmin);

    List<UmsAdmin> findUmsAdminList(UmsAdmin umsAdmin);

    void updateUmsAdmin(UmsAdmin umsAdmin);

    void batchInsertUmsAdmin(UmsAdmin umsAdmin);
}
