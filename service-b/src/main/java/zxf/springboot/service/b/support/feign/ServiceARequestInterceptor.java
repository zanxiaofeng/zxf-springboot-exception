package zxf.springboot.service.b.support.feign;


import feign.RequestTemplate;
import feign.auth.BasicAuthRequestInterceptor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServiceARequestInterceptor extends BasicAuthRequestInterceptor {
    public ServiceARequestInterceptor() {
        super("davis", "davis");
    }

    @Override
    public void apply(RequestTemplate template) {
        super.apply(template);
        log.info("ServiceARequestInterceptor::apply X-AGE {}", template.headers().get("X-AGE"));
    }
}
