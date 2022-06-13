package zxf.springboot.service.a.rest;

import org.springframework.web.bind.annotation.*;
import zxf.springboot.checking.annotation.UserRightCheck;
import zxf.springboot.service.a.support.exception.AErrorCodes;
import zxf.springboot.support.exception.BusinessException;
import zxf.springboot.support.rest.ServerResponse;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/{id}")
    @UserRightCheck(express = "#isAdmin() and #hasRights('1','2') and (#hasRight('2') or #hasRight('9'))")
    public ServerResponse<User> findById(@PathVariable String id, @RequestHeader(name = "X-AGE", required = false) Integer age) {

        if ("1".equals(id)) {
            throw new BusinessException(AErrorCodes.A_BUS_ERR_001.newDescription("Can not find a user with Id " + id));
        }
        if ("2".equals(id)) {
            throw new RuntimeException("unkown error");
        }
        return ServerResponse.success(new User("3", "Davis", age));
    }
}
