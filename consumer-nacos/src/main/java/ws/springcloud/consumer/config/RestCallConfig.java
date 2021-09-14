package ws.springcloud.consumer.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


/**
 * @author WindShadow
 * @version 2021-08-08.
 */
@Configuration
public class RestCallConfig {

    @LoadBalanced // ribbon 提供的负载均衡
    @Bean
    public RestTemplate ribbonRestTemplate() {

        return new RestTemplate();
    }
}
