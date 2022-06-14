package zxf.springboot.checking.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import zxf.springboot.checking.interceptor.UserRightCheckInterceptor;

@Slf4j
@Configuration
@ComponentScan("zxf.springboot.checking")
@ConditionalOnProperty(prefix = "zxf", name = "checking.url")
public class AutoConfigurationChecking implements WebMvcConfigurer {
    @Value("${zxf.checking.url}")
    private String checkingUrl;

    @Autowired
    private UserRightCheckInterceptor userRightCheckInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("addInterceptors, {}", checkingUrl);
        registry.addInterceptor(userRightCheckInterceptor)
                .addPathPatterns(checkingUrl);
    }
}
