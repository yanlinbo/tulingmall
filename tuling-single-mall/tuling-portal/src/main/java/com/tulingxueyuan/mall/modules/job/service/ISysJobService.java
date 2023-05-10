package com.tulingxueyuan.mall.modules.job.service;

import com.tulingxueyuan.mall.modules.job.modle.SysJob;

import java.util.List;

public interface ISysJobService {
    List<SysJob> selectJobList(SysJob sysJob);

    SysJob selectJobById(Long jobId);

    boolean run(SysJob job);
}
