package icu.yunke.framework.web.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet工具类，通过RequestContextHolder获取request和response对象
 */
public class ServletUtils {

    private static final ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

    /**
     * request对象
     * @return
     */
    public static HttpServletRequest getRequest() {
        return requestAttributes.getRequest();
    }

    /**
     * response对象
     * @return
     */
    public static HttpServletResponse getResponse() {
        return requestAttributes.getResponse();
    }

    /**
     * 获取int型
     * @param key
     * @return
     */
    public static int getIntParameter(String key) {
        return Integer.parseInt(getRequest().getParameter(key));
    }

    /**
     * 获取long型
     * @param key
     * @return
     */
    public static long getLongParameter(String key) {
        return Long.parseLong(getRequest().getParameter(key));
    }

    /**
     * 获取String型
     * @param key
     * @return
     */
    public static String getStringParameter(String key) {
        return getRequest().getParameter(key);
    }

}
