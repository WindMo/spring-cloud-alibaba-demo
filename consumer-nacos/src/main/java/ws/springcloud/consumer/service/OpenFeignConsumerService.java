package ws.springcloud.consumer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ws.springcloud.consumer.service.fallbakfactory.ConsumerServiceFallbackFactory;


/**
 * @author WindShadow
 * @version 2021-08-09.
 */

/**
 * 使用feign的客户端注解绑定远程
 * 服务的名称远程服务的名称可以大写，也可以小写
 */
//@FeignClient(name = "provider-nacos",path = "", fallback = ConsumerServiceFallBack.class) // 指定熔断器
@FeignClient(name = "provider-nacos",path = "", fallbackFactory = ConsumerServiceFallbackFactory.class)// 指定熔断器工厂
public interface OpenFeignConsumerService extends ConsumerService{

    @GetMapping("/hello")
    String getHello();

    @GetMapping("/hashcode")
    int getHashCodeValue(@RequestParam(value = "str") String str);

    @GetMapping("/error")
    String callError();

    @GetMapping("/timeout")
    String timeout(@RequestParam("time") Integer time);
}
