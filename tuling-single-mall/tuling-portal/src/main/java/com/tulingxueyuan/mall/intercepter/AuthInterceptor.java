package com.tulingxueyuan.mall.intercepter;

import com.tulingxueyuan.mall.common.api.ResultCode;
import com.tulingxueyuan.mall.common.exception.ApiException;
import com.tulingxueyuan.mall.common.util.ComConstants;
import com.tulingxueyuan.mall.common.util.JwtTokenUtil;
import com.tulingxueyuan.mall.modules.ums.model.UmsMember;
import com.tulingxueyuan.mall.modules.ums.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 作用： 验证 用户是否登录、菜单资源权限
 * 作者：徐庶
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    // 配置文件中的白名单secure.ignored.urls
    private List<String> urls;

    @Autowired
    private UmsMemberService memberService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1、不需要登录就可以访问的路径——白名单
        // 获取当前请求   /admin/login
        String requestURI = request.getRequestURI();
        // Ant方式路径匹配 /**  ？  _
        PathMatcher matcher = new AntPathMatcher();
        // 因为session基于cookie,解决cookie的跨站不能共享的新特性问题（课后同学反馈所加，很多同学浏览器中有这个新特性）
        response.setHeader("SET-COOKIE", "JSESSIONID=" + request.getSession().getId() + ";Path=/;secure;HttpOnly;SameSite=None");
        for (String ignoredUrl : urls) {
            if(matcher.match(ignoredUrl,requestURI)){
                return  true;
            }
        }
//        String userName = request.getHeader("Authorization");
        //获得jwt
        String jwt = request.getHeader("tokenHeader");
        if(StringUtils.isEmpty(jwt) && !jwt.startsWith(tokenHead)){
            throw new ApiException(ResultCode.UNAUTHORIZED);
        }
        jwt = jwt.substring(tokenHead.length());
        String userName = jwtTokenUtil.getUserNameFromToken(jwt);
        if(StringUtils.isEmpty(userName)){
            throw new ApiException(ResultCode.UNAUTHORIZED);
        }
        UmsMember member = memberService.getMemberByUsername(userName);
        if(member == null){
            throw new ApiException(ResultCode.UNAUTHORIZED);
        }
        JwtTokenUtil.CURRENT_USERNAME.set(userName);
//        request.getSession().setAttribute(ComConstants.FLAG_CURRENT_USER,member);
//        //2、未登录用户，直接拒绝访问
//        if (null == request.getSession().getAttribute(ComConstants.FLAG_CURRENT_USER)) {
//            throw new ApiException(ResultCode.UNAUTHORIZED);
//        }
//        else {
//            //3、已登录用户，判断是否有资源访问权限  Todo:到时候用spring security实现
//            UmsMember umsMember = (UmsMember) request.getSession().getAttribute(ComConstants.FLAG_CURRENT_USER);
//            // 获取用户所有可访问资源
//            List<UmsResource> resourceList = memberService.getResourceList(umsMember.getId());
//            for (UmsResource umsResource : resourceList) {
//                if(matcher.match( umsResource.getUrl(),requestURI)){
//                    return  true;
//                }
//            }
//            throw new ApiException(ResultCode.FORBIDDEN);
//        }
        return  true;
    }


    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }
}
