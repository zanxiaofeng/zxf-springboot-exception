package zxf.springboot.service.b.feign.model;

import lombok.Data;

@Data
public class CreateUserRequest {
    private final String id;
    private final String name;
    private final Integer age;
}
