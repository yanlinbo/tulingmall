package com.tulingxueyuan.mall.modules.ums.service.impl;

import com.tulingxueyuan.mall.common.service.RedisService;
import com.tulingxueyuan.mall.modules.ums.mapper.UmsMemberMapper;
import com.tulingxueyuan.mall.modules.ums.model.UmsMember;
import com.tulingxueyuan.mall.modules.ums.service.UmsMemberCacheService;;
import com.tulingxueyuan.mall.modules.ums.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 后台用户缓存管理Service实现类
 * Created by macro on 2020/3/13.
 */
@Service
public class UmsMemberCacheServiceImpl implements UmsMemberCacheService {
    @Autowired
    private UmsMemberService memberService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private UmsMemberMapper memberMapper;
//    @Autowired
//    private UmsAdminRoleRelationService adminRoleRelationService;
    @Value("${redis.database}")
    private String REDIS_DATABASE;
    @Value("${redis.expire.common}")
    private Long REDIS_EXPIRE;
    @Value("${redis.key.admin}")
    private String REDIS_KEY_ADMIN;
    @Value("${redis.key.resourceList}")
    private String REDIS_KEY_RESOURCE_LIST;

    @Override
    public void delAdmin(Long memberId) {
        UmsMember member = memberService.getById(memberId);
        if (member != null) {
            String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + member.getUsername();
            redisService.del(key);
        }
    }

    @Override
    public void delResourceList(Long adminId) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":" + adminId;
        redisService.del(key);
    }

    @Override
    public void delResourceListByRole(Long roleId) {
//        QueryWrapper<UmsAdminRoleRelation> wrapper = new QueryWrapper<>();
//        wrapper.lambda().eq(UmsAdminRoleRelation::getRoleId,roleId);
//        List<UmsAdminRoleRelation> relationList = adminRoleRelationService.list(wrapper);
//        if (CollUtil.isNotEmpty(relationList)) {
//            String keyPrefix = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":";
//            List<String> keys = relationList.stream().map(relation -> keyPrefix + relation.getAdminId()).collect(Collectors.toList());
//            redisService.del(keys);
//        }
    }

    @Override
    public void delResourceListByRoleIds(List<Long> roleIds) {
//        QueryWrapper<UmsAdminRoleRelation> wrapper = new QueryWrapper<>();
//        wrapper.lambda().in(UmsAdminRoleRelation::getRoleId,roleIds);
//        List<UmsAdminRoleRelation> relationList = adminRoleRelationService.list(wrapper);
//        if (CollUtil.isNotEmpty(relationList)) {
//            String keyPrefix = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":";
//            List<String> keys = relationList.stream().map(relation -> keyPrefix + relation.getAdminId()).collect(Collectors.toList());
//            redisService.del(keys);
//        }
    }

    @Override
    public void delResourceListByResource(Long resourceId) {
//        List<Long> adminIdList = adminMapper.getAdminIdList(resourceId);
//        if (CollUtil.isNotEmpty(adminIdList)) {
//            String keyPrefix = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":";
//            List<String> keys = adminIdList.stream().map(adminId -> keyPrefix + adminId).collect(Collectors.toList());
//            redisService.del(keys);
//        }
    }

    @Override
    public UmsMember getMember(String username) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + username;
        return (UmsMember) redisService.get(key);
    }

    @Override
    public void setMember(UmsMember admin) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + admin.getUsername();
        redisService.set(key, admin, REDIS_EXPIRE);
    }

//    @Override
//    public List<UmsResource> getResourceList(Long adminId) {
//        String key = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":" + adminId;
//        return (List<UmsResource>) redisService.get(key);
//    }
//
//    @Override
//    public void setResourceList(Long adminId, List<UmsResource> resourceList) {
//        String key = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":" + adminId;
//        redisService.set(key, resourceList, REDIS_EXPIRE);
//    }
}
