package zxf.springboot.service.b.support.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zxf.springboot.service.b.support.feign.ServiceAProperties;

@Configuration
public class ServiceAConfig {
    @Bean("service-a")
    @ConfigurationProperties(prefix = "service-a")
    ServiceAProperties serviceAProperties() {
        return new ServiceAProperties();
    }
}
