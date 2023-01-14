package com.tulingxueyuan.mall.modules.ums.service.impl;

import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tulingxueyuan.mall.common.exception.ApiException;
import com.tulingxueyuan.mall.common.exception.Asserts;
import com.tulingxueyuan.mall.common.util.ComConstants;
import com.tulingxueyuan.mall.common.util.JwtTokenUtil;
import com.tulingxueyuan.mall.modules.ums.mapper.UmsMemberLoginLogMapper;
import com.tulingxueyuan.mall.modules.ums.mapper.UmsMemberMapper;
import com.tulingxueyuan.mall.modules.ums.model.UmsMember;
import com.tulingxueyuan.mall.modules.ums.model.UmsMemberLoginLog;
import com.tulingxueyuan.mall.modules.ums.service.UmsMemberCacheService;
import com.tulingxueyuan.mall.modules.ums.service.UmsMemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author XuShu
 * @since 2023-01-07
 */
@Service
public class UmsMemberServiceImpl extends ServiceImpl<UmsMemberMapper, UmsMember> implements UmsMemberService {


    @Autowired
    UmsMemberLoginLogMapper memberLoginLogMapper;

    @Autowired
    UmsMemberCacheService memberCacheService;

    @Autowired
    HttpSession session;

    @Override
    public UmsMember register(UmsMember umsMemberParam) {
        UmsMember umsMember = new UmsMember();
        BeanUtils.copyProperties(umsMemberParam, umsMember);
        umsMember.setCreateTime(new Date());
        umsMember.setStatus(1);
        //查询是否有相同用户名的用户
        QueryWrapper<UmsMember> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(UmsMember::getUsername,umsMember.getUsername());
        List<UmsMember> umsAdminList = list(wrapper);
        if (umsAdminList.size() > 0) {
            return null;
        }
        //将密码进行加密操作
        String encodePassword = BCrypt.hashpw(umsMember.getPassword());
        umsMember.setPassword(encodePassword);
        baseMapper.insert(umsMember);
        return umsMember;
    }

    @Override
    public UmsMember login(String username, String password) {
        //密码需要客户端加密后传递
        UmsMember umsMember=null;
        try {
            umsMember = loadUserByUsername(username);
            //密码加密校验还没搞明白  先去掉加密
            if(!BCrypt.checkpw(password,umsMember.getPassword())){
                Asserts.fail("密码不正确");
            }
            /*if(!userDetails.isEnabled()){
                Asserts.fail("帐号已被禁用");
            }*/
            insertLoginLog(username);
        } catch (Exception e) {
            Asserts.fail("登录异常:"+e.getMessage());
        }
        return umsMember;
    }

    private void insertLoginLog(String username) {
        UmsMember member = getMemberByUsername(username);
        if(member==null) return;
        UmsMemberLoginLog loginLog = new UmsMemberLoginLog();
        loginLog.setMemberId(member.getId());
        loginLog.setCreateTime(new Date());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        loginLog.setIp(request.getRemoteAddr());
        memberLoginLogMapper.insert(loginLog);
    }

    private UmsMember loadUserByUsername(String username) {
        //获取用户信息
        UmsMember admin = getMemberByUsername(username);
        if (admin != null) {
            // 查询用户访问资源，暂留， 后续改动
            // List<UmsResource> resourceList = getResourceList(admin.getId());
            return admin;
        }
        throw new ApiException("用户不存在");
    }

    public UmsMember getMemberByUsername(String username) {
        UmsMember member = memberCacheService.getMember(username);
        if(member!=null) return  member;
        QueryWrapper<UmsMember> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(UmsMember::getUsername,username);
        List<UmsMember> adminList = list(wrapper);
        if (adminList != null && adminList.size() > 0) {
            member = adminList.get(0);
            memberCacheService.setMember(member);
            return member;
        }
        return null;
    }

    public UmsMember getCurrentMember(){
//        UmsMember member = (UmsMember) session.getAttribute(ComConstants.FLAG_MEMBER_USER);
        //jwt改造
        String userName = JwtTokenUtil.CURRENT_USERNAME.get();
        if(StringUtils.isEmpty(userName)){
            UmsMember member = this.getMemberByUsername(userName);
            return member;
        }
        return null;
    }



}
