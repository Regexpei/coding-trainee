package cn.regexp.coding.trainee.aop;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.IdUtil;
import cn.regexp.coding.trainee.anno.ApiOprLogAnno;
import cn.regexp.coding.trainee.common.Constants;
import cn.regexp.coding.trainee.entity.ApiOprLog;
import cn.regexp.coding.trainee.utils.IpUtil;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Regexpei
 * @date 2024/5/19 15:43
 * @description API操作切面类
 */
@Slf4j
@Aspect
@Component
public class ApiOprAspect {

    @Value("${spring.application.name}")
    private String moduleName;

    /**
     * 从请求中获取 IP
     *
     * @return IP
     */
    private static String getIpFromRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            HttpServletRequest request = (HttpServletRequest) requestAttributes
                    .resolveReference(RequestAttributes.REFERENCE_REQUEST);
            return IpUtil.getRealIp(request);
        }
        return Constants.UNKNOWN;
    }

    @Pointcut("@annotation(cn.regexp.coding.trainee.anno.ApiOprLogAnno)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String id = IdUtil.fastSimpleUUID();
        Object result;
        try {
            // 执行方法前操作
            executeBefore(proceedingJoinPoint, id);
            result = proceedingJoinPoint.proceed();
            // 执行方法后操作
            executeAfter(proceedingJoinPoint, id, result);
        } catch (Throwable ex) {
            // 执行方法异常后操作
            executeAfterEx(ex, id);
            throw ex;
        }
        return result;
    }

    private void executeBefore(ProceedingJoinPoint proceedingJoinPoint, String id) {
        // 获取目标方法的签名信息
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        // 从方法签名中获取 ApiOprLogAnno 注解的信息
        ApiOprLogAnno apiOprLogAnno = signature.getMethod().getAnnotation(ApiOprLogAnno.class);

        // 封装 ApiOprLog 对象
        ApiOprLog apiOprLog = packaging(id, getIpFromRequest(), signature.toString(), apiOprLogAnno);
        if (apiOprLogAnno.isSaveRequest()) {
            // 保存请求参数
            // 获取方法签名的参数名数组
            String[] parameterNames = signature.getParameterNames();
            // 获取连接点传递的实参数组
            Object[] args = proceedingJoinPoint.getArgs();
            Map<String, Object> paramMap = new HashMap<>(parameterNames.length);

            for (int i = 0; i < parameterNames.length; i++) {
                if (!RequestAttributes.REFERENCE_REQUEST.equals(parameterNames[i])) {
                    paramMap.put(parameterNames[i], args[i]);
                }
            }
            apiOprLog.setReqParams(JSON.toJSONString(paramMap));
        }

        // 入库操作
        log.debug("executeBefore apiOprLog: {}", JSON.toJSONString(apiOprLog));
    }

    private void executeAfter(ProceedingJoinPoint proceedingJoinPoint, String id, Object result) {
        // 获取目标方法的签名信息
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        // 从方法签名中获取 ApiOprLogAnno 注解的信息
        ApiOprLogAnno apiOprLogAnno = signature.getMethod().getAnnotation(ApiOprLogAnno.class);
        if (!apiOprLogAnno.isSaveResponse()) {
            return;
        }

        ApiOprLog apiOprLog = new ApiOprLog();
        apiOprLog.setId(id);
        apiOprLog.setResResult(JSON.toJSONString(result));
        apiOprLog.setUpdateTime(DateTime.now());

        // 入库操作
        log.debug("executeAfter apiOprLog: {}", JSON.toJSONString(apiOprLog));
    }

    private void executeAfterEx(Throwable ex, String id) {
        ApiOprLog apiOprLog = new ApiOprLog();
        apiOprLog.setId(id);
        apiOprLog.setExMessage(ex.toString());
        apiOprLog.setExJson(ExceptionUtil.stacktraceToString(ex));
        apiOprLog.setUpdateTime(DateTime.now());

        // 入库操作
        log.debug("executeAfterEx apiOprLog: {}", JSON.toJSONString(apiOprLog));
    }

    /**
     * 封装 ApiOprLog
     *
     * @param id            主键
     * @param sourceIp      IP
     * @param method        方法
     * @param apiOprLogAnno 注解
     * @return 接口操作日志对象
     */
    private ApiOprLog packaging(String id,
                                String sourceIp,
                                String method,
                                ApiOprLogAnno apiOprLogAnno) {
        ApiOprLog apiOprLog = new ApiOprLog();
        apiOprLog.setId(id);
        apiOprLog.setSourceIp(sourceIp);
        apiOprLog.setUsername("Regexp");
        apiOprLog.setMethod(method);
        apiOprLog.setApiModule(moduleName);
        apiOprLog.setApiType(apiOprLogAnno.apiType());
        apiOprLog.setApiDetail(apiOprLogAnno.apiDetail());
        apiOprLog.setCreateTime(DateTime.now());
        return apiOprLog;
    }
}
