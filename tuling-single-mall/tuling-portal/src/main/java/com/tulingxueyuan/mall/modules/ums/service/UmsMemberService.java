package com.tulingxueyuan.mall.modules.ums.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.modules.ums.model.UmsMember;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author XuShu
 * @since 2023-01-07
 */
public interface UmsMemberService extends IService<UmsMember> {

    /**
     * 注册功能
     */
    UmsMember register(UmsMember umsMember);

    /**
     * 登录功能
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    UmsMember login(String username, String password);

    /**
     * 获取当前用户
     * @return
     */
    UmsMember getCurrentMember();

    UmsMember getMemberByUsername(String username);

}
