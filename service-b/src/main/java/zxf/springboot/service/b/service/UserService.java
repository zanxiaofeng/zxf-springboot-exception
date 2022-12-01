package zxf.springboot.service.b.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zxf.springboot.service.a.feign.ErrorCodes;
import zxf.springboot.service.a.feign.ServiceAClient;
import zxf.springboot.service.a.feign.model.CreateUserRequest;
import zxf.springboot.service.a.feign.model.CreateUserResponse;
import zxf.springboot.service.a.feign.model.FindUserByIdResponse;
import zxf.springboot.service.a.feign.model.User;

@Service
public class UserService {
    @Autowired
    private ServiceAClient serviceAClient;

    public User create(CreateUserRequest request) {
        CreateUserResponse user = serviceAClient.createUser(request);
        return user.getData();
    }

    public User findById(String id, Integer age) {
        FindUserByIdResponse user = serviceAClient.findUserById(id, age);
        if (user.hasError(ErrorCodes.A_BIZ_ERR_001)) {
            return new User(id, id + "-new", age);
        }
        return user.getData();
    }
}
