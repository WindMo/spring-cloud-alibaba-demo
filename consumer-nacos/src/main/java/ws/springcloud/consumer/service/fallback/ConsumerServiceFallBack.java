package ws.springcloud.consumer.service.fallback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ws.springcloud.consumer.service.OpenFeignConsumerService;

/**
 *
 * 使用 OpenFeign进行熔断器的实现
 * @author WindShadow
 * @version 2021-08-08.
 */
@Slf4j
@Component
public class ConsumerServiceFallBack implements OpenFeignConsumerService {

    @Override
    public String getHello() {

        log.warn("ConsumerService#getHello方法远程调用发生异常");
        return "error";
    }

    @Override
    public int getHashCodeValue(String str) {

        log.warn("ConsumerService#getHashCodeValue(String)方法远程调用发生异常，str: {}",str);
        return -1;
    }

    @Override
    public String callError() {

        return "error(FallBack)";
    }

    @Override
    public String timeout(Integer time) {
        return "error(callError)";
    }
}
