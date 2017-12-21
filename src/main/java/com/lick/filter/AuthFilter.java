package com.lick.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @description： 鉴权过滤
 * @author: lick
 * @date: 2017年12月21日 13:26
 * @copyright: 版权归所有
 */
@Component
public class AuthFilter extends ZuulFilter {

    private static Logger logger = LoggerFactory.getLogger(AuthFilter.class);

//    @Value("${excludeList}")
    String excludeList;
    @Override
    public String filterType() {
        /** 再转发之前进行过滤 */
        return "pre";
    }

    @Override
    public int filterOrder() {
        /** 过滤器的排序顺序 */
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        /** 是否对所有请求进行过滤 */
        String url = RequestContext.getCurrentContext().getRequest().getRequestURI();
        if(StringUtils.isNotBlank(excludeList) && excludeList.equals(url)){
            return false;
        }
        return true;
    }

    @Override
    public Object run() {
        /** 获取当前的上下文 */
        RequestContext ctx = RequestContext.getCurrentContext();
        /** 获取请求信息 */
        HttpServletRequest requst = ctx.getRequest();
        logger.info("send {} request to {}",requst.getMethod(),requst.getRequestURI());
        HttpSession session = requst.getSession();
        /** 是否存在令牌 */
        Object accessToken = session.getAttribute("accessToken");
        if(accessToken == null){
            session.setAttribute("accessToken","11111");
        }
        logger.info("access token ok");
        return null;
    }
}
