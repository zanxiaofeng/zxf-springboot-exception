package zxf.springboot.service.b.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zxf.springboot.service.a.feign.ServiceAClient;
import zxf.springboot.service.a.feign.model.FindUserByIdResponse;
import zxf.springboot.service.a.feign.model.User;

@Service
public class UserService {
    @Autowired
    private ServiceAClient serviceAClient;

    public User findById(String id, Integer age) {
        FindUserByIdResponse user = serviceAClient.findUserById(id, age);
        return user.getData();
    }
}
