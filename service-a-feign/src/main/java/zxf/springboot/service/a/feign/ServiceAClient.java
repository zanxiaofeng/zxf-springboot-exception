package zxf.springboot.service.a.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import zxf.springboot.service.a.feign.model.CreateUserRequest;
import zxf.springboot.service.a.feign.model.CreateUserResponse;
import zxf.springboot.service.a.feign.model.FindUserByIdResponse;

@FeignClient(value = "service-a", url = "${service-a.url}")
public interface ServiceAClient {
    @PostMapping("/users")
    CreateUserResponse create(@RequestBody CreateUserRequest request);

    @GetMapping("/users/{userId}")
    FindUserByIdResponse findById(@PathVariable String userId);
}
