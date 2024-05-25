package cn.regexp.coding.trainee.web.filter;

import cn.hutool.core.util.IdUtil;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static cn.regexp.coding.trainee.common.Constants.TRACE_ID;


/**
 * @author Regexpei
 * @date 2024/5/19 22:28
 * @description 日志过滤器
 */
@Component
public class LogFilter extends GenericFilter {
    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        // 获取链路 ID
        String traceId = httpServletRequest.getHeader(TRACE_ID);
        if (traceId == null || traceId.isEmpty()) {
            // 通过雪花算法获取分布式 ID
            traceId = IdUtil.getSnowflakeNextIdStr();
            MDC.put(TRACE_ID, traceId);
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        MDC.remove(TRACE_ID);
    }
}
