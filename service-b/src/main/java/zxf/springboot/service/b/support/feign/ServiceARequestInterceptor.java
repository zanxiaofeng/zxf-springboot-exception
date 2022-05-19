package zxf.springboot.service.b.support.feign;


import feign.auth.BasicAuthRequestInterceptor;

public class ServiceARequestInterceptor extends BasicAuthRequestInterceptor {
    public ServiceARequestInterceptor() {
        super("davis", "davis");
    }
}
