package ws.springcloud.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class ProviderNacosApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderNacosApplication.class, args);
    }

}
