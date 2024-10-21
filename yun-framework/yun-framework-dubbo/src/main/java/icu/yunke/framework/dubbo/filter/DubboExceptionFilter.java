package icu.yunke.framework.dubbo.filter;

import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.filter.ExceptionFilter;
import org.apache.dubbo.rpc.service.GenericService;

import static org.apache.dubbo.common.constants.CommonConstants.CONSUMER;
import static org.apache.dubbo.common.constants.CommonConstants.PROVIDER;

@Activate(group = {PROVIDER, CONSUMER})
public class DubboExceptionFilter extends ExceptionFilter {

    @Override
    public void onResponse(Result appResponse, Invoker<?> invoker, Invocation invocation) {
        // 如果有异常
        if (appResponse.hasException() && GenericService.class != invoker.getInterface()) {
            // 获取抛出的异常
            Throwable exception = appResponse.getException();
            String classname = exception.getClass().getName();
            // 如果是自定义异常，直接抛出
            if (classname.startsWith("icu.yunke.framework.common.exception")) {
                return;
            }
            // 如果是其他异常，使用Dubbo的业务进行处理
            super.onResponse(appResponse, invoker, invocation);
        }
    }

}
