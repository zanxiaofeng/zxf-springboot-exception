package zxf.springboot.service.b.support.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.auth.BasicAuthRequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import zxf.springboot.support.Spring.ApplicationContextProvider;

@Slf4j
public class ServiceARequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        ServiceAProperties serviceAProperties = ApplicationContextProvider.getApplicationContext()
                .getBean(ServiceAProperties.class);
        BasicAuthRequestInterceptor basicAuthRequestInterceptor = new BasicAuthRequestInterceptor(serviceAProperties.getUsername(),
                serviceAProperties.getPassword());
        basicAuthRequestInterceptor.apply(template);
        log.info("ServiceARequestInterceptor::apply X-AGE {}", template.headers().get("X-AGE"));
    }
}
