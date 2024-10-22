package icu.yunke.framework.web.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    /**
     * 将字符串渲染到客户端,默认状态码为200
     * 需要指定状态码可以使用重载方法
     * @param response 渲染对象
     * @param string 待渲染的字符串
     */
    public static void renderString(HttpServletResponse response, String string) {
        renderString(response, HttpServletResponse.SC_OK, string);
    }

    /**
     * 将字符串渲染到客户端
     *
     * @param response 渲染对象
     * @param string 待渲染的字符串
     */
    public static void renderString(HttpServletResponse response, int status, String string) {
        try {
            response.setStatus(status);
            response.setContentType("application/json;charset=UTF-8");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
