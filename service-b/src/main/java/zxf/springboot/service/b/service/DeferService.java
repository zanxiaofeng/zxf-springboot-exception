package zxf.springboot.service.b.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zxf.springboot.service.a.feign.ServiceAClient;
import zxf.springboot.service.a.feign.model.DeferResponse;

@Service
public class DeferService {
    @Autowired
    private ServiceAClient serviceAClient;

    public String successDefer() {
        DeferResponse response = serviceAClient.successDefer();
        return response.getData();
    }

    public String timeoutDefer() {
        DeferResponse response = serviceAClient.timeoutDefer();
        return response.getData();
    }

    public String errorDefer() {
        DeferResponse response = serviceAClient.errorDefer();
        return response.getData();
    }
}
