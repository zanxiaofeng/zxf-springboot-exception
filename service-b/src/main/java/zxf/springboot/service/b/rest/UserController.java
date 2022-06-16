package zxf.springboot.service.b.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zxf.springboot.service.a.feign.model.CreateUserRequest;
import zxf.springboot.service.a.feign.model.User;
import zxf.springboot.service.b.service.UserService;
import zxf.springboot.support.rest.ServerResponse;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ServerResponse<User> create(@RequestBody CreateUserRequest request) {
        return ServerResponse.success(userService.create(request));
    }

    @GetMapping("/{id}")
    public ServerResponse<User> findById(@PathVariable String id, @RequestParam(required = false) Integer age) {
        return ServerResponse.success(userService.findById(id, age));
    }
}
