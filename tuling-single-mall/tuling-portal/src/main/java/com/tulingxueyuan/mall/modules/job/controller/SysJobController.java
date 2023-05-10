package com.tulingxueyuan.mall.modules.job.controller;

import com.tulingxueyuan.mall.common.annotation.YlbLog;
import com.tulingxueyuan.mall.common.enums.BusinessType;
import com.tulingxueyuan.mall.common.web.BaseController;
import com.tulingxueyuan.mall.common.web.domain.AjaxResult;
import com.tulingxueyuan.mall.common.web.page.TableDataInfo;
import com.tulingxueyuan.mall.common.util.ExcelUtil;
import com.tulingxueyuan.mall.modules.job.modle.SysJob;
import com.tulingxueyuan.mall.modules.job.service.ISysJobService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 调度任务信息操作处理
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/job")
public class SysJobController extends BaseController {

    @Autowired
    private ISysJobService jobService;

    /**
     * 查询定时任务列表
     */
//    @RequiresPermissions("monitor:job:list")
    @GetMapping("/list")
    public TableDataInfo list(SysJob sysJob){

        //1,设置请求分页数据

        //2,查询定时任务列表
        List<SysJob> list = jobService.selectJobList(sysJob);
        //3,响应请求分页数据
        return getDataTable(list);
    }



    /**
     * 导出定时任务列表
     */
//    @RequiresPermissions("monitor:job:export")
//    @Log(title = "定时任务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysJob sysJob){

        List<SysJob> list = jobService.selectJobList(sysJob);
        ExcelUtil<SysJob> util = new ExcelUtil<SysJob>(SysJob.class);
        util.exportExcel(response, list, "定时任务");

    }

    /**
     * 获取定时任务详细信息
     */
//    @RequiresPermissions("monitor:job:query")
    @GetMapping(value = "/{jobId}")
    public AjaxResult getInfo(@PathVariable("jobId") Long jobId)
    {
        return success(jobService.selectJobById(jobId));
    }

    /**
     * 新增定时任务
     */

    /**
     * 修改定时任务
     */

    /**
     * 定时任务状态修改
     */

    /**
     * 定时任务立即执行一次
     */
//    @RequiresPermissions("monitor:job:changeStatus")
    @YlbLog(module = "定时任务", businessType = BusinessType.UPDATE)
    @PutMapping("/run")
    public AjaxResult run(@RequestBody SysJob job) throws SchedulerException {

        boolean result = jobService.run(job);
        return result ? success() : error("任务不存在或已过期！");
    }

    /**
     * 删除定时任务
     */
}
