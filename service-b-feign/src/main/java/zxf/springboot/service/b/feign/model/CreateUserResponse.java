package zxf.springboot.service.b.feign.model;

import lombok.Data;
import zxf.springboot.support.feign.ClientResponse;

@Data
public class CreateUserResponse extends ClientResponse<User> {
}
