package zxf.springboot.service.b.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import zxf.springboot.service.a.feign.ServiceAClient;
import zxf.springboot.service.a.feign.model.FindUserByIdResponse;
import zxf.springboot.service.a.feign.model.User;
import zxf.springboot.service.b.support.exception.BErrorCodes;
import zxf.springboot.support.exception.BusinessException;

@Service
public class UserService {
    @Autowired
    private ServiceAClient serviceAClient;

    @GetMapping("/{id}")
    public User findById(@PathVariable String id) {
        FindUserByIdResponse user = serviceAClient.findById(id);
        if (!user.isSuccess()) {
            throw new BusinessException(BErrorCodes.B_BUS_ERR_002.withCause(user.getError()));
        }
        return user.getData();
    }
}
