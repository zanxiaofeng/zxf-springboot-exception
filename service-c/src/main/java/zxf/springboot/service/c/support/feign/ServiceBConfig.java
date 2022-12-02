package zxf.springboot.service.c.support.feign;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceBConfig {
    @Bean("service-b")
    @ConfigurationProperties(prefix = "service-b")
    ServiceBProperties serviceBProperties() {
        return new ServiceBProperties();
    }
}
