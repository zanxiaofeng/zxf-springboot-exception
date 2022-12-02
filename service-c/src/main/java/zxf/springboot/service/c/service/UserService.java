package zxf.springboot.service.c.service;

import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zxf.springboot.service.b.feign.ErrorCodes;
import zxf.springboot.service.b.feign.ServiceBClient;
import zxf.springboot.service.b.feign.model.CreateUserRequest;
import zxf.springboot.service.b.feign.model.CreateUserResponse;
import zxf.springboot.service.b.feign.model.FindUserByIdResponse;
import zxf.springboot.service.b.feign.model.User;
import zxf.springboot.service.c.support.exception.BizErrorCodes;
import zxf.springboot.support.exception.BusinessException;

@Service
public class UserService {
    @Autowired
    private ServiceBClient serviceBClient;

    public User create(CreateUserRequest request) {
        try {
            CreateUserResponse user = serviceBClient.createUser(request);
            if (user.isSuccess()) {
                return user.getData();
            }
            throw new BusinessException(BizErrorCodes.C_BIZ_ERR_002.withCause(user.getError()));
        } catch (FeignException feignException) {
            throw new BusinessException(BizErrorCodes.C_BIZ_ERR_001, feignException);
        }
    }

    public User findById(String id, Integer age) {
        try {
            FindUserByIdResponse user = serviceBClient.findUserById(id, age);
            if (user.isSuccess()) {
                return user.getData();
            }
            throw new BusinessException(BizErrorCodes.C_BIZ_ERR_002.withCause(user.getError()));
        } catch (FeignException feignException) {
            throw new BusinessException(BizErrorCodes.C_BIZ_ERR_001, feignException);
        }
    }
}
