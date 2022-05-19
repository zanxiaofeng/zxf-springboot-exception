package zxf.springboot.service.a.feign.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class User {
    private final String id;
    private final String name;
}
