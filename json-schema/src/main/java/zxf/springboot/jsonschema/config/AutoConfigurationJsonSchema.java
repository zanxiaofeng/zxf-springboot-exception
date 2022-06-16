package zxf.springboot.jsonschema.config;

import lombok.extern.slf4j.Slf4j;
import org.everit.json.schema.Schema;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import zxf.springboot.jsonschema.provider.JsonSchemaProvider;

@Slf4j
@Configuration
@ConditionalOnClass(Schema.class)
@ComponentScan("zxf.springboot.jsonschema")
public class AutoConfigurationJsonSchema {
    @Bean
    public JsonSchemaProvider jsonSchemaProvider() {
        log.info("jsonSchemaProvider");
        return JsonSchemaProvider.fromCacheProvider();
    }
}
