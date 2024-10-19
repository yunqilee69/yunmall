package icu.yunke.framework.common.context;

import icu.yunke.framework.common.model.dto.UserDTO;

/**
 * 用户上下文
 */
public class UserContextHolder {

    // TODO dubbo进行请求时，也需要设置

    private static final ThreadLocal<UserDTO> contextHolder = new ThreadLocal<>();

    /**
     * 设置当前线程的UserDTO
     * @param userDTO 用户信息
     */
    public static void set(UserDTO userDTO) {
        contextHolder.set(userDTO);
    }

    /**
     * 获取当前线程的UserDTO
     * @return 用户信息
     */
    public static UserDTO get() {
        return contextHolder.get();
    }

    /**
     * 清除当前线程的UserDTO
     */
    public static void clear() {
        contextHolder.remove();
    }
}
