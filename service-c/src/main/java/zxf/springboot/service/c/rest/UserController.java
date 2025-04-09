package zxf.springboot.service.c.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import zxf.springboot.service.b.feign.model.CreateUserRequest;
import zxf.springboot.service.b.feign.model.User;
import zxf.springboot.service.c.service.UserService;
import zxf.springboot.service.c.support.retrofit.RetrofitFactory;
import zxf.springboot.service.c.support.retrofit.ServiceClient;
import zxf.springboot.support.rest.ServerResponse;

import java.time.Duration;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Value("${service-b.url}")
    private String serviceBUrl;

    @PostMapping
    public ServerResponse<User> create(@RequestBody CreateUserRequest request) {
        return ServerResponse.success(userService.create(request));
    }

    @GetMapping("/{id}")
    public ServerResponse<User> findById(@PathVariable String id, @RequestParam(required = false) Integer age) {
        return ServerResponse.success(userService.findById(id, age));
    }

    @PostMapping("/retrofit")
    public ServerResponse<User> retrofitCreate(@RequestBody CreateUserRequest request) {
        ServiceClient serviceClient = RetrofitFactory.buildServiceClient(serviceBUrl, Duration.ofSeconds(10));

        return ServerResponse.success(serviceClient.createUser(request).getData());
    }

    @GetMapping("retrofit/{id}")
    public ServerResponse<User> retrofitFindById(@PathVariable String id, @RequestParam(required = false) Integer age) {
        ServiceClient serviceClient = RetrofitFactory.buildServiceClient(serviceBUrl, Duration.ofSeconds(10));
        return ServerResponse.success(serviceClient.findUserById(id, age).getData());
    }
}
