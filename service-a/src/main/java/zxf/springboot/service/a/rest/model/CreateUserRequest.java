package zxf.springboot.service.a.rest.model;

import lombok.Data;

@Data
public class CreateUserRequest {
    private final String id;
    private final String name;
    private final Integer age;

    public User toUser() {
        return new User(id, name, age);
    }
}
