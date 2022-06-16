package zxf.springboot.jsonschema.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

@Slf4j
public class FileUtils {
    public static InputStream getInputStream(String url) throws IOException {
        log.info("getInputStream({})", url);
        if (url.toLowerCase(Locale.ROOT).startsWith("classpath:")) {
            return new ClassPathResource(url.substring(10)).getInputStream();
        }
        return new FileInputStream(ResourceUtils.getFile(url));
    }
}
