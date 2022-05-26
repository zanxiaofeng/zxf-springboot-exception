package zxf.springboot.service.b.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import zxf.springboot.service.b.service.DeferService;
import zxf.springboot.service.b.service.UserService;
import zxf.springboot.support.rest.ServerResponse;

import java.util.concurrent.ForkJoinPool;

@Slf4j
@RestController
@RequestMapping("/defers")
public class DeferController {
    @Autowired
    private DeferService deferService;

    @GetMapping("/success")
    public ServerResponse<String> success() {
        return ServerResponse.success(deferService.successDefer());
    }

    @GetMapping("/timeout")
    public ServerResponse<String> timeout() {
        return ServerResponse.success(deferService.timeoutDefer());
    }

    @GetMapping("/error")
    public ServerResponse<String> error() {
        return ServerResponse.success(deferService.errorDefer());
    }
}
