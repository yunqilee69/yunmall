package icu.yunke.framework.dubbo.filter;

import com.alibaba.fastjson2.JSON;
import icu.yunke.framework.common.constants.UserConstant;
import icu.yunke.framework.common.context.UserContextHolder;
import icu.yunke.framework.common.model.dto.UserDTO;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

import static org.apache.dubbo.common.constants.CommonConstants.CONSUMER;

@Activate(group = CONSUMER)
public class ConsumerFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        // 转换为json字符串，在调用者服务中在转换回来
        UserDTO userDTO = UserContextHolder.get();
        String userStr = JSON.toJSONString(userDTO);
        // 设置上下文到dubbo上下文中，以便在调用者服务中获取
        RpcContext.getClientAttachment().setAttachment(UserConstant.USER_ID, userStr);
        return invoker.invoke(invocation);
    }
}
