package cn.hippo4j.example.decorator;


import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;

import java.util.Map;

/**
 * eg: 上下文MDC信息拷贝装饰器
 *
 * @author imyzt
 * @date 2021/11/22
 */
public class ContextCopyingDecorator implements TaskDecorator {

    @Override
    public Runnable decorate(Runnable runnable) {
        Map<String, String> previous = MDC.getCopyOfContextMap();
        // other context...
        return () -> {
            try {
                MDC.setContextMap(previous);
                runnable.run();
            } finally {
                MDC.clear();
            }
        };
    }
}
