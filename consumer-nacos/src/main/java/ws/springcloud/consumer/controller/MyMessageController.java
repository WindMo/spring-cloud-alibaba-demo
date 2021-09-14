package ws.springcloud.consumer.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ws.springcloud.consumer.config.MyMessageConfig;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author WindShadow
 * @version 2021-08-18.
 */
@Slf4j
@RestController
@RequestMapping("/msg")
public class MyMessageController {

    @Autowired
    protected MyMessageConfig myMessageConfig;

/** 因为 {@link MyMessageConfig}加了{@link RefreshScope}注解修饰，所以{@link #myMessageConfig} 对象不能直接返回 */
//    @GetMapping("/info")
//    public MyMessageConfig myMessageConfig() {
//
//        return myMessageConfig;
//    }


    @GetMapping("/info")
    public MyMessageConfig myMessageConfig() {

        return copy(myMessageConfig); // 不能直接返回被代理的对象
    }

    @PostConstruct
    public void init() {

        log.info("MyMessageController Controller初始化 {}", new SimpleDateFormat("yyy-HH-dd HH:mm:ss").format(new Date()));
    }

    private static MyMessageConfig copy(MyMessageConfig myMessageConfig) {

       return new MyMessageConfig(myMessageConfig.getMessage(),myMessageConfig.getVersion());
    }
}
