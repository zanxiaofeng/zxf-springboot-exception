package zxf.springboot.service.b.rest;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Product {
    private final String id;
    private final String name;
}
