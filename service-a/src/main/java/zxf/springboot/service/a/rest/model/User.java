package zxf.springboot.service.a.rest.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class User {
    private final String id;
    private final String name;
    private final Integer age;
}
