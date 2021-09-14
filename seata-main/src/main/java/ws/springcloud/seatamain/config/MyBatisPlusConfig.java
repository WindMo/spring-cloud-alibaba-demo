package ws.springcloud.seatamain.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// 扫描我们的 mapper 包
@MapperScan("ws.springcloud.seatamain.dao.mapper")
@EnableTransactionManagement
@Configuration // 配置类
public class MyBatisPlusConfig {

}
