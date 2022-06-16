package zxf.springboot.jsonschema.provider;

import lombok.extern.slf4j.Slf4j;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

@Slf4j
@Component
public class JsonSchemaProvider {
    public Schema load(String url) {
        log.info("load({})", url);
        try {
            JSONObject jsonSchemaObject = new JSONObject(new JSONTokener(getInputStream(url)));
            return SchemaLoader.load(jsonSchemaObject);
        } catch (IOException ex) {
            throw new IllegalStateException("Can't load json schema from " + url, ex);
        }
    }

    private InputStream getInputStream(String url) throws IOException {
        if (url.toLowerCase(Locale.ROOT).startsWith("classpath:")) {
            return new ClassPathResource(url.substring(10)).getInputStream();
        }
        return new FileInputStream(ResourceUtils.getFile(url));
    }
}
