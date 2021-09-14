package ws.springcloud.consumer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ws.springcloud.consumer.service.ConsumerService;

/**
 * @author WindShadow
 * @version 2021-08-08.
 */
@Slf4j
@RestController
public class ConsumerController {

    @Autowired
    public ConsumerService consumerService;

    @GetMapping("/sayHello")
    public String sayHello(String str) {

        log.info("str: {}",str);
        return str == null ? consumerService.getHello() : String.valueOf(consumerService.getHashCodeValue(str));
    }

    @GetMapping("/sayError")
    public String sayError() {

        return consumerService.callError();
    }

    @GetMapping("/exception")
    public String exception() {

        throw new RuntimeException();
    }

    @GetMapping("/timeout")
    public String timeout() {

        return consumerService.timeout(null);
    }
}
