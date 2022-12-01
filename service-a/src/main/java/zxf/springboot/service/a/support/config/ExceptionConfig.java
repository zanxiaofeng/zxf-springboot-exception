package zxf.springboot.service.a.support.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zxf.springboot.service.a.support.exception.SysErrorCodes;
import zxf.springboot.support.exception.SystemError;

@Configuration
public class ExceptionConfig {
    @Bean
    SystemError unexpectedErrorCode() {
        return SysErrorCodes.A_SYS_ERR_000;
    }
}
