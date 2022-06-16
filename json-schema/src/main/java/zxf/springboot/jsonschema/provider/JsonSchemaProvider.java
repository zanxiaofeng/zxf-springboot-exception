package zxf.springboot.jsonschema.provider;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import zxf.functional.core.Caching;
import zxf.springboot.jsonschema.util.FileUtils;

import java.io.IOException;
import java.io.InputStream;

@FunctionalInterface
public interface JsonSchemaProvider {
    Schema load(String url);

    static Schema fromFileProvider(String url) {
        try (InputStream schemaInputStream = FileUtils.getInputStream(url)) {
            JSONObject jsonSchemaObject = new JSONObject(new JSONTokener(schemaInputStream));
            return SchemaLoader.load(jsonSchemaObject);
        } catch (IOException ex) {
            throw new IllegalStateException("Can't load json schema from " + url, ex);
        }
    }

    static JsonSchemaProvider fromCacheProvider() {
        return Caching.cachedFunction(JsonSchemaProvider::fromFileProvider)::apply;
    }
}
