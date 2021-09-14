package ws.springcloud.provider.controller;

import lombok.SneakyThrows;
import org.bouncycastle.util.Objects;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author WindShadow
 * @version 2021-08-08.
 */

@RestController
public class ProductDemoController {

    @Value("${server.port}")
    public Integer serverPort;

    @GetMapping("/hello")
    public String hello() {

        return "Hello World!" + serverPort;
    }

    @GetMapping("/hashcode")
    public Integer hashcode(String str) {

        return Objects.hashCode(str);
    }

    @GetMapping("/myerror")
    public String callError() {

        throw new RuntimeException();
    }

    @SneakyThrows
    @GetMapping("/timeout")
    public String timeout(Integer time) {


        Integer t = time == null ? 10 : time;
        TimeUnit.SECONDS.sleep(t);
        return "timeout return" + serverPort;
    }
}
