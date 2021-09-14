package ws.springcloud.consumer.config;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author WindShadow
 * @version 2021-08-18.
 */

@Slf4j
@ConfigurationProperties(prefix = "mymsg")
@Component

// 以这种方式注释的bean可以在运行时刷新，任何使用它们的组件将在下一个方法调用中获得一个新实例，完全初始化并注入所有依赖项。
@RefreshScope // 实际上spring会对该实例进行代理
@Getter
@Setter
public class MyMessageConfig {

    @Autowired
    protected Environment environment;
    private String message;
    private Integer version;

    public MyMessageConfig() {
    }

    public MyMessageConfig(String message, Integer version) {
        this.message = message;
        this.version = version;
    }

    @PostConstruct
    public void init() {

        log.info("MyMessageConfig Bean初始化 message:{} version:{} {}",message, version, new SimpleDateFormat("yyy-HH-dd HH:mm:ss").format(new Date()));
    }
}
