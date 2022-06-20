package zxf.springboot.jsonschema.advice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.everit.json.schema.Schema;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;
import zxf.springboot.jsonschema.annotation.JsonSchemaValidation;
import zxf.springboot.jsonschema.provider.JsonSchemaProvider;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

@Slf4j
@ControllerAdvice
public class JsonSchemaValidationRequestBodyAdvice extends RequestBodyAdviceAdapter {
    @Autowired
    private JsonSchemaProvider jsonSchemaProvider;

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        Boolean result = methodParameter.hasMethodAnnotation(JsonSchemaValidation.class);
        log.info("supports, {}.{} = {}", methodParameter.getMethod().getDeclaringClass().getSimpleName(), methodParameter.getMethod().getName(), result);
        return result;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        log.info("beforeBodyRead");

        JsonSchemaValidation jsonSchemaValidation = parameter.getMethodAnnotation(JsonSchemaValidation.class);
        Schema jsonSchema = jsonSchemaProvider.load(jsonSchemaValidation.value());
        try (InputStream bodyStream = inputMessage.getBody()) {
            byte[] bodyContent = StreamUtils.copyToByteArray(bodyStream);
            jsonSchema.validate(new JSONObject(new JSONTokener(new ByteArrayInputStream(bodyContent))));
            return new MyHttpInputMessage(bodyContent, inputMessage.getHeaders());
        }
    }

    @RequiredArgsConstructor
    public static class MyHttpInputMessage implements HttpInputMessage {
        private final byte[] bodyContent;
        private final HttpHeaders headers;

        @Override
        public InputStream getBody() throws IOException {
            return new ByteArrayInputStream(bodyContent);
        }

        @Override
        public HttpHeaders getHeaders() {
            return headers;
        }
    }
}
