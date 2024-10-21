package icu.yunke.framework.dubbo.filter;

import com.alibaba.fastjson2.JSON;
import icu.yunke.framework.common.constants.UserConstant;
import icu.yunke.framework.common.context.UserContextHolder;
import icu.yunke.framework.common.model.dto.UserDTO;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

import static org.apache.dubbo.common.constants.CommonConstants.PROVIDER;

@Activate(group = PROVIDER)
public class ProviderFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {

        // 获取userDTO并设置到上下文中
        String userStr = RpcContext.getServerAttachment().getAttachment(UserConstant.USER_ID);
        UserDTO userDTO = JSON.parseObject(userStr, UserDTO.class);
        UserContextHolder.set(userDTO);

        // 执行远程逻辑
        Result result = invoker.invoke(invocation);

        // 结束前，需要将上下文清除
        UserContextHolder.clear();
        return result;
    }
}
