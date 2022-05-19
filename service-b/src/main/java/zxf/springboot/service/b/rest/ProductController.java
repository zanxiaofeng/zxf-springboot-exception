package zxf.springboot.service.b.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zxf.springboot.service.b.support.exception.BErrorCodes;
import zxf.springboot.support.exception.BusinessException;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping("/{id}")
    public Product findById(@PathVariable String id) {
        if ("1".equals(id)) {
            throw new BusinessException(BErrorCodes.B_BUS_ERR_001.newDescription("Can not find a product with Id " + id));
        }
        throw new RuntimeException("unkown error");
    }
}
