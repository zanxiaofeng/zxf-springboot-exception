package zxf.springboot.service.a.feign.model;

import lombok.Data;
import zxf.springboot.support.feign.ClientResponse;

@Data
public class FindUserByIdResponse extends ClientResponse<User> {
}
