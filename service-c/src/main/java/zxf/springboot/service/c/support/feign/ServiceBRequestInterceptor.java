package zxf.springboot.service.c.support.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.auth.BasicAuthRequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import zxf.springboot.support.Spring.ApplicationContextProvider;

@Slf4j
public class ServiceBRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        ServiceBProperties serviceAProperties = (ServiceBProperties) ApplicationContextProvider.getApplicationContext()
                .getBean("service-b");
        BasicAuthRequestInterceptor basicAuthRequestInterceptor = new BasicAuthRequestInterceptor(serviceAProperties.getUsername(),
                serviceAProperties.getPassword());
        basicAuthRequestInterceptor.apply(template);
        log.info("ServiceARequestInterceptor::apply X-AGE {}", template.headers().get("X-AGE"));
    }
}
