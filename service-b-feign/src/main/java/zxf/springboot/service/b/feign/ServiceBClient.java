package zxf.springboot.service.b.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import zxf.springboot.service.b.feign.model.CreateUserRequest;
import zxf.springboot.service.b.feign.model.CreateUserResponse;
import zxf.springboot.service.b.feign.model.DeferResponse;
import zxf.springboot.service.b.feign.model.FindUserByIdResponse;

@FeignClient(value = "service-b", url = "${service-b.url}")
public interface ServiceBClient {
    @PostMapping("/users")
    CreateUserResponse createUser(@RequestBody CreateUserRequest request);

    @GetMapping("/users/{userId}")
    FindUserByIdResponse findUserById(@PathVariable String userId, @RequestHeader(name = "X-AGE") Integer age);

    @GetMapping("/defers/success")
    DeferResponse successDefer();

    @GetMapping("/defers/timeout")
    DeferResponse timeoutDefer();

    @GetMapping("/defers/error")
    DeferResponse errorDefer();
}
