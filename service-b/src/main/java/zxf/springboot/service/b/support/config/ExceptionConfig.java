package zxf.springboot.service.b.support.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import zxf.springboot.service.b.support.exception.BizErrorCodes;
import zxf.springboot.service.b.support.exception.SysErrorCodes;
import zxf.springboot.support.exception.SystemError;

@Configuration
public class ExceptionConfig {
    @Bean
    SystemError unexpectedErrorCode() {
        return SysErrorCodes.B_SYS_ERR_000;
    }
}
