package zxf.springboot.service.a.rest;

import org.springframework.web.bind.annotation.*;
import zxf.springboot.service.a.support.exception.AErrorCodes;
import zxf.springboot.support.exception.BusinessException;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/{id}")
    public User findById(@PathVariable String id) {
        if ("1".equals(id)) {
            throw new BusinessException(AErrorCodes.A_ERR_BUS_001.newDescription("Can not find a user with Id " + id));
        }
        throw new RuntimeException("unkown error");
    }
}
