package ws.springcloud.consumer.service.fallbakfactory;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ws.springcloud.consumer.service.ConsumerService;
import ws.springcloud.consumer.service.fallback.ConsumerServiceFallBack;


/**
 * @author WindShadow
 * @version 2021-08-10.
 */

@Slf4j
@Component
public class ConsumerServiceFallbackFactory implements FallbackFactory<ConsumerService> {

    @Autowired
    private ConsumerServiceFallBack fallBack;

    @Override
    public ConsumerService create(Throwable t) {

        log.error("ConsumerService 远程调用发生异常",t);
        return fallBack;
    }
}
