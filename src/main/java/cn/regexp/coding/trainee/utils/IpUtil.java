package cn.regexp.coding.trainee.utils;

import cn.regexp.coding.trainee.common.Constants;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Regexpei
 * @date 2024/5/19 16:10
 * @description IP工具类
 */
public class IpUtil {

    private static final String[] HEADER_NAMES =
            new String[]{"x-Forwarded-For", "Proxy-Client-IP", "wL-Proxy-Client-IP", "HTTP_CLIENT_IP"};

    /**
     * 获取真实IP
     *
     * @param request 请求对象
     * @return  IP
     */
    public static String getRealIp(HttpServletRequest request) {
        if (request == null) {
            return Constants.UNKNOWN;
        }

        for (String s : HEADER_NAMES) {
            String ip = request.getHeader(s);

            if (ip != null && !ip.isEmpty() && Constants.UNKNOWN.equals(ip)) {
                return ip;
            }
        }

        return request.getRemoteAddr();
    }
}