package cn.regexp.coding.trainee.aop;

import cn.hutool.core.util.IdUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;

import static cn.regexp.coding.trainee.common.Constants.TRACE_ID;


/**
 * @author Regexpei
 * @date 2024/5/19 22:38
 * @description 日志切面类
 */
@Aspect
@Component
public class LogAspect {

    /*
        此处为需要进行添加 traceId 相关注解请求，比如 MQ 相关等
        如：org.springframework.cloud.stream.annotation.StreamListener
        此处以 GetMapping 为例，测试此处时，记得将 LogFilter#@Component 注释掉
     */
    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String traceId = IdUtil.getSnowflakeNextIdStr();
        // 获取请求属性对象
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            // 获取 request 对象
            HttpServletRequest request = (HttpServletRequest) requestAttributes
                    .resolveReference(RequestAttributes.REFERENCE_REQUEST);
            // 是否存在 traceId
            if (request != null && request.getHeader(TRACE_ID) != null) {
                traceId = request.getHeader(TRACE_ID);
            }
        }

        MDC.put(TRACE_ID, traceId);
        Object result = proceedingJoinPoint.proceed();
        MDC.remove(TRACE_ID);
        return result;
    }
}
