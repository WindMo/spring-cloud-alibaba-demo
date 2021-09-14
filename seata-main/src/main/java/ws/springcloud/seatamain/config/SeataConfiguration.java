package ws.springcloud.seatamain.config;

import io.seata.rm.datasource.DataSourceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * seata相关配置
 * @author WindShadow
 * @version 2021-08-26.
 */

@Configuration
public class SeataConfiguration {

    /**
     * 数据源代理
     * @param dataSource SpringBoot2.0默认使用{@link com.zaxxer.hikari.HikariDataSource}连接池
     * @return
     */
//    @Primary
    @Bean
    public DataSourceProxy dataSourceProxy(@Autowired DataSource dataSource) {

        return new DataSourceProxy(dataSource);
    }
}
